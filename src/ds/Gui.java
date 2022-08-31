package ds;

import ds.linear.*;
import ds.nonlinear.*;
import static ds.Main.LINKEDLISTSTRING;
import static ds.Main.DYNAMICARRAYSTRING;
import static ds.Main.QUEUESTRING;
import static ds.Main.STACKSTRING;
import static ds.Main.BSTSTRING;
import static ds.Main.GRAPHMATRIXSTRING;
// import static ds.Main.GRAPHLISTSTRING;
// import static ds.Main.HASHSTRING;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Gui extends JPanel implements ActionListener {
    final static int extraWindowWidth = 100;
    static String mode;
    final static String createString = "Create";
    final static String readString = "Read";
    final static String updateString = "Update";
    final static String deleteString = "Delete";
    JLabel modeLabel;
    ValuePanel valuePanel;
    CountPanel countPanel;
    JButton buttonSubmit;
    String dataStructure;
    ReadPanel readPanel;
    
    // For graph (matrix)
    ValuePanel updateValuePanel;
    CountPanel weightPanel;
    ValuePanel directionPanel;

    IntLinkedList intLinkedList;
    DoubleLinkedList doubleLinkedList;
    IntDynamicArray intDynamicArray;
    DoubleDynamicArray doubleDynamicArray;
    IntQueue intQueue;
    DoubleQueue doubleQueue;
	IntStack intStack;
	DoubleStack doubleStack;
	IntBST intBST;
	DoubleBST doubleBST;
	IntGraphMatrix intGraphMatrix;
	// DoubleGraphMatrix doubleGraphMatrix;
	// IntGraphList intGraphList;
	// DoubleGraphList doubleGraphList;
	// IntHashMap intHashMap;
	// DoubleHashMap doubleHashMap;

    public Gui (String dataStructure) {
        super(null);
        this.mode = "";
        this.dataStructure = dataStructure;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initialise data structures
        switch (dataStructure) {
        	case LINKEDLISTSTRING:
	        	this.intLinkedList = new IntLinkedList();
	        	this.doubleLinkedList = new DoubleLinkedList();
        		break;
        	case DYNAMICARRAYSTRING:
        		this.intDynamicArray = new IntDynamicArray();
        		this.doubleDynamicArray = new DoubleDynamicArray();
        		break;
        	case QUEUESTRING:
        		this.intQueue = new IntQueue();
				this.doubleQueue = new DoubleQueue();
        		break;
        	case STACKSTRING:
        		this.intStack = new IntStack();
				this.doubleStack = new DoubleStack();
        		break;
        	case BSTSTRING:
        		this.intBST = new IntBST();
				this.doubleBST = new DoubleBST();
        		break;
        	case GRAPHMATRIXSTRING:
        		this.intGraphMatrix = new IntGraphMatrix();
				// this.doubleGraphMatrix = new DoubleGraphMatrix();
        		break;
    //     	case GRAPHLISTSTRING:
    //     		// this.intGraphList = new IntGraphList();
				// // this.doubleGraphList = new DoubleGraphList();
    //     		break;
    //     	case HASHSTRING:
    //     		// this.intHashMap = new IntHashMap();
				// // this.doubleHashMap = new DoubleHashMap();
    //     		break;
        	default:
        		break;
        }

        // Create control buttons
        JButton createButton = new JButton(createString);
        createButton.addActionListener(this);
        createButton.setActionCommand(createString);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setFocusable(false);

        JButton readButton = new JButton(readString);
        readButton.addActionListener(this);
        readButton.setActionCommand(readString);
        readButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        readButton.setFocusable(false);

        JButton updateButton = new JButton(updateString);
        updateButton.addActionListener(this);
        updateButton.setActionCommand(updateString);
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton.setFocusable(false);
        if (this.dataStructure == QUEUESTRING || this.dataStructure == STACKSTRING) {
        	updateButton.setVisible(false);
        }

        JButton deleteButton = new JButton(deleteString);
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand(deleteString);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setFocusable(false);

        // Add control buttons
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.setPreferredSize(new Dimension(300, 40));
        controls.setMaximumSize(controls.getPreferredSize());
        controls.add(createButton);
        controls.add(readButton);
        controls.add(updateButton);
        controls.add(deleteButton);
        this.add(controls);

        // Mode header
        modeLabel = new JLabel();
        modeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modeLabel.setForeground(Color.black);
        this.add(modeLabel);
        modeLabel.setVisible(true);

        // START OF valuePanel
        valuePanel = new ValuePanel(this.dataStructure);
        // Hide panel for create elements
        this.add(valuePanel);
        valuePanel.setVisible(false);

        // Add action listeners
        valuePanel.getButtonIntInput().addActionListener(this);
        valuePanel.getButtonDoubleInput().addActionListener(this);
        // buttonStrInput.addActionListener(this);
        // buttonBoolInput.addActionListener(this);
        // buttonTrue.addActionListener(this);
        // buttonFalse.addActionListener(this);
        // END OF valuePanel

        // START OF countPanel
        countPanel = new CountPanel(dataStructure);
        this.add(countPanel);
        countPanel.setVisible(false);
        // END OF countPanel

        // For graph (matrix)
        if (this.dataStructure == GRAPHMATRIXSTRING) {
            updateValuePanel = new ValuePanel("");
            this.add(updateValuePanel);
            updateValuePanel.setVisible(false);
            updateValuePanel.getPanelInputType().setVisible(false);

            weightPanel = new CountPanel(DYNAMICARRAYSTRING);
            this.add(weightPanel);
            weightPanel.setVisible(false);

            directionPanel = new ValuePanel("Direction");
            this.add(directionPanel);
            directionPanel.setVisible(false);
            directionPanel.getIntSpinner().setVisible(false);
            directionPanel.getDoubleSpinner().setVisible(false);
        }

        // START OF readNodeList
        readPanel = new ReadPanel(dataStructure);
        this.add(readPanel);
        readPanel.setVisible(false);
        // END OF readNodeList

        // Submit Button
        buttonSubmit = new JButton();
        buttonSubmit.setActionCommand("Submit");
        buttonSubmit.addActionListener(this);
        buttonSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSubmit.setFocusable(false);
        this.add(buttonSubmit);
        buttonSubmit.setVisible(false);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand().equals(createString)) {
            this.mode = createString;
            modeLabel.setText(this.mode);
		    valuePanel.getValueLabel().setText("New value:");
            switch (this.dataStructure) {
            	case LINKEDLISTSTRING:
            	case BSTSTRING:
		            countPanel.getCountLabel().setText("New count:");
		            countPanel.setVisible(true);
		        	break;
		        case DYNAMICARRAYSTRING:
		        	countPanel.getCountLabel().setText("Insert at index:");
		        	countPanel.setVisible(true);
		        	break;
		        case QUEUESTRING:
		        case STACKSTRING:
		        	countPanel.setVisible(false);
		        	break;
		       	case GRAPHMATRIXSTRING:
		        	countPanel.setVisible(false);
                    updateValuePanel.setVisible(false);
		        	break;
		        default:
		        	break;
            }
            buttonSubmit.setText(createString);

            valuePanel.setVisible(true);
            readPanel.setVisible(false);
            buttonSubmit.setVisible(true);
        } else if (e.getActionCommand().equals(readString)) {
            this.mode = readString;
            modeLabel.setText(this.mode);
            if (valuePanel.getButtonIntInput().isSelected()) {
                this.runDataStructure(0, 0);
            } else if (valuePanel.getButtonDoubleInput().isSelected()) {
                this.runDataStructure(0.0d, 0);
            }

            switch (this.dataStructure) {
                case GRAPHMATRIXSTRING:
                    updateValuePanel.setVisible(false);
                    weightPanel.setVisible(false);
                    directionPanel.setVisible(false);

                    if (valuePanel.getButtonDoubleInput().isSelected()) {
                        this.runDataStructure(0, 0, 0, "");
                    }
                    break;
                default:
                    break;
            }

            valuePanel.setVisible(false);
            countPanel.setVisible(false);
            buttonSubmit.setVisible(false);
            readPanel.setVisible(true);
        } else if (e.getActionCommand().equals(updateString)) {
            this.mode = updateString;
            modeLabel.setText(this.mode);
            switch (this.dataStructure) {
            	case LINKEDLISTSTRING:
            	case BSTSTRING:
                    valuePanel.getValueLabel().setText("Value to update:");
		            countPanel.getCountLabel().setText("Updated count:");
                    countPanel.setVisible(true);
		        	break;
		        case DYNAMICARRAYSTRING:
                    valuePanel.getValueLabel().setText("New value:");
		        	countPanel.getCountLabel().setText("Update element at index:");
                    countPanel.setVisible(true);
		        	break;
                case GRAPHMATRIXSTRING:
                    valuePanel.getValueLabel().setText("Value to update:");
                    updateValuePanel.getValueLabel().setText("New value:");
                    updateValuePanel.setVisible(true);
                    countPanel.setVisible(false);
                    break;
		        default:
		        	break;
            }
            buttonSubmit.setText(updateString);

            valuePanel.setVisible(true);
            buttonSubmit.setVisible(true);
            readPanel.setVisible(false);
        } else if (e.getActionCommand().equals(deleteString)) {
            this.mode = deleteString;
            modeLabel.setText(this.mode);
            switch (this.dataStructure) {
            	case LINKEDLISTSTRING:
            	case BSTSTRING:
		            valuePanel.getValueLabel().setText("Value to delete:");
		            valuePanel.setVisible(true);
            		countPanel.setVisible(false);
		        	break;
		        case DYNAMICARRAYSTRING:
		        	countPanel.getCountLabel().setText("Delete element at index:");
		            valuePanel.setVisible(false);
            		countPanel.setVisible(true);
		        	break;
		        case QUEUESTRING:
		        case STACKSTRING:
		            valuePanel.setVisible(false);
            		countPanel.setVisible(false);
		        	break;
                case GRAPHMATRIXSTRING:
                    valuePanel.getValueLabel().setText("Vertex to delete:");
                    valuePanel.setVisible(true);
                    countPanel.setVisible(false);
                    updateValuePanel.setVisible(false);
		        default:
		        	break;
            }
            buttonSubmit.setText(deleteString);

            buttonSubmit.setVisible(true);
            readPanel.setVisible(false);
        } else if (e.getActionCommand().equals("Submit")) {
            if (valuePanel.getButtonIntInput().isSelected()) {
                // stringInputField.setVisible(false);
                // panelBool.setVisible(false);
                switch (this.dataStructure) {
                    case GRAPHMATRIXSTRING:
                        this.runDataStructure(
                            (Integer) valuePanel.getIntSpinner().getValue(),
                            (Integer) updateValuePanel.getIntSpinner().getValue()
                        );
                        break;
                    default:
                        this.runDataStructure(
                            (Integer) valuePanel.getIntSpinner().getValue(),
                            (Integer) countPanel.getCountInputField().getValue()
                        );
                        break;
                }
            } else if (valuePanel.getButtonDoubleInput().isSelected()) {
                // stringInputField.setVisible(false);
                // panelBool.setVisible(false);
                switch (this.dataStructure) {
                    case GRAPHMATRIXSTRING:
                        this.runDataStructure(
                            (Integer) valuePanel.getIntSpinner().getValue(),
                            (Integer) updateValuePanel.getIntSpinner().getValue(),
                            (Integer) weightPanel.getCountInputField().getValue(),
                            (String) directionPanel.getInputGroup().getSelection().getActionCommand()
                        );
                        break;
                    default:
                        this.runDataStructure(
                            (Double) valuePanel.getDoubleSpinner().getValue(),
                            (Integer) countPanel.getCountInputField().getValue()
                        );
                        break;
                }
            }
        }

        if (valuePanel.getButtonIntInput().isSelected()) {
            // stringInputField.setVisible(false);
            // panelBool.setVisible(false);
            switch (this.dataStructure) {
                case GRAPHMATRIXSTRING:
                    valuePanel.getIntSpinner().setVisible(true);
                    valuePanel.getDoubleSpinner().setVisible(false);
                    updateValuePanel.getIntSpinner().setVisible(true);
                    updateValuePanel.getDoubleSpinner().setVisible(false);

                    if (this.mode == updateString) {
                        valuePanel.getValueLabel().setText("Value to update:");
                        updateValuePanel.getValueLabel().setText("New value:");
                        updateValuePanel.setVisible(true);
                    } else if (this.mode == createString) {
                        valuePanel.getValueLabel().setText("New value:");
                        updateValuePanel.setVisible(false);
                    } else if (this.mode == deleteString) {
                        valuePanel.getValueLabel().setText("Vertex to delete:");
                        updateValuePanel.setVisible(false);
                    } else {
                        updateValuePanel.setVisible(false);
                    }

                    weightPanel.setVisible(false);
                    directionPanel.setVisible(false);
                    break;
                default:
                    valuePanel.getIntSpinner().setVisible(true);
                    valuePanel.getDoubleSpinner().setVisible(false);
                    break;
            }
        } else if (valuePanel.getButtonDoubleInput().isSelected()) {
            // stringInputField.setVisible(false);
            // panelBool.setVisible(false);
            switch (this.dataStructure) {
                case GRAPHMATRIXSTRING:
                    valuePanel.getIntSpinner().setVisible(true);
                    valuePanel.getDoubleSpinner().setVisible(false);
                    updateValuePanel.getIntSpinner().setVisible(true);
                    updateValuePanel.getDoubleSpinner().setVisible(false);
                    weightPanel.getCountInputField().setVisible(true);
                    directionPanel.getPanelInputType().setVisible(true);

                    switch (this.mode) {
                        case createString:
                        case updateString:
                            valuePanel.getValueLabel().setText("First vertex:");
                            updateValuePanel.getValueLabel().setText("Second vertex:");
                            weightPanel.getCountLabel().setText("Weight:");
                            updateValuePanel.setVisible(true);
                            weightPanel.setVisible(true);
                            directionPanel.setVisible(true);
                            break;
                        case readString:
                            updateValuePanel.setVisible(false);
                            weightPanel.setVisible(false);
                            directionPanel.setVisible(false);
                            break;
                        case deleteString:
                            valuePanel.getValueLabel().setText("First vertex:");
                            updateValuePanel.getValueLabel().setText("Second vertex:");
                            updateValuePanel.setVisible(true);
                            weightPanel.setVisible(false);
                            directionPanel.setVisible(true);
                            break;
                        default:
                            break;
                    }
                        break;
                default:
                    valuePanel.getIntSpinner().setVisible(false);
                    valuePanel.getDoubleSpinner().setVisible(true);
                    break;
            }
        }

        // } else if (buttonStrInput.isSelected()) {
        //     valuePanel.getIntSpinner().setVisible(false);
        //     valuePanel.getDoubleSpinner().setVisible(false);
        //     stringInputField.setVisible(true);
        //     panelBool.setVisible(false);
        //     System.out.println(stringInputField.getText());
        // } else if (buttonBoolInput.isSelected()) {
        //     valuePanel.getIntSpinner().setVisible(false);
        //     valuePanel.getDoubleSpinner().setVisible(false);
        //     stringInputField.setVisible(false);
        //     panelBool.setVisible(true);
        //     if (buttonTrue.isSelected()) {
        //         System.out.println("true");
        //     } else if (buttonFalse.isSelected()) {
        //         System.out.println("false");
        //     }
    }

    public void runDataStructure (Integer value, Integer count) {
        switch (this.dataStructure) {
            case LINKEDLISTSTRING:
                switch (this.mode) {
                    case createString:
                        this.intLinkedList.create(value, count);
                        break;
                    case readString:
                        readIntNode(this.intLinkedList.read());
                        break;
                    case updateString:
                        this.intLinkedList.update(value, count);
                        break;
                    case deleteString:
                        this.intLinkedList.delete(value);
                        break;
                    default:
                        break;
                }
                if (this.intLinkedList.root != null) {
                    valuePanel.getButtonDoubleInput().setEnabled(false);
                } else {
                    valuePanel.getButtonDoubleInput().setEnabled(true);
                }
                break;
            case DYNAMICARRAYSTRING:
                switch (this.mode) {
                    case createString:
                        this.intDynamicArray.create(value, count);
                        break;
                    case readString:
                        readDynamicArray(this.intDynamicArray.read());
                        break;
                    case updateString:
                        this.intDynamicArray.update(value, count);
                        break;
                    case deleteString:
                        this.intDynamicArray.delete(count);
                        break;
                    default:
                        break;
                }
                if (this.intDynamicArray.arr == null) {
                	valuePanel.getButtonDoubleInput().setEnabled(true);
                } else if (this.intDynamicArray.arr.length > 0) {
                    valuePanel.getButtonDoubleInput().setEnabled(false);
                } else {
                    valuePanel.getButtonDoubleInput().setEnabled(true);
                }
                break;
            case QUEUESTRING:
                switch (this.mode) {
                    case createString:
                        this.intQueue.create(value);
                        break;
                    case readString:
                        readIntQueueStack(this.intQueue.read(), this.intQueue.getLength());
                        break;
                    case deleteString:
                        this.intQueue.delete();
                        break;
                    default:
                        break;
                }
                if (this.intQueue.head != null && this.intQueue.tail != null) {
                    valuePanel.getButtonDoubleInput().setEnabled(false);
                } else {
                    valuePanel.getButtonDoubleInput().setEnabled(true);
                }
                break;
            case STACKSTRING:
                switch (this.mode) {
                    case createString:
                        this.intStack.create(value);
                        break;
                    case readString:
                        readIntQueueStack(this.intStack.read(), this.intStack.getLength());
                        break;
                    case deleteString:
                        this.intStack.delete();
                        break;
                    default:
                        break;
                }
                if (this.intStack.top != null) {
                    valuePanel.getButtonDoubleInput().setEnabled(false);
                } else {
                    valuePanel.getButtonDoubleInput().setEnabled(true);
                }
                break;
            case BSTSTRING:
                switch (this.mode) {
                    case createString:
                        this.intBST.create(value, count);
                        break;
                    case readString:
                        readIntNode(this.intBST.read());
                        break;
                    case updateString:
                        this.intBST.update(value, count);
                        break;
                    case deleteString:
                        this.intBST.delete(value);
                        break;
                    default:
                        break;
                }
                if (this.intBST.root != null) {
                    valuePanel.getButtonDoubleInput().setEnabled(false);
                } else {
                    valuePanel.getButtonDoubleInput().setEnabled(true);
                }
                break;
            case GRAPHMATRIXSTRING:
                switch (this.mode) {
                    case createString:
                        this.intGraphMatrix.createVertex(value);
                        break;
                    case readString:
                        readIntMatrix(this.intGraphMatrix.readMatrix(), this.intGraphMatrix.readVertices());
                        break;
                    case updateString:
                        this.intGraphMatrix.updateVertex(value, count);
                        break;
                    case deleteString:
                        this.intGraphMatrix.deleteVertex(value);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
    public void runDataStructure (Double value, Integer count) {
        switch (this.dataStructure) {
            case LINKEDLISTSTRING:
                switch (this.mode) {
                    case createString:
                        this.doubleLinkedList.create(value, count);
                        break;
                    case readString:
                        readDoubleNode(this.doubleLinkedList.readValue(), this.doubleLinkedList.readCount());
                        break;
                    case updateString:
                        this.doubleLinkedList.update(value, count);
                        break;
                    case deleteString:
                        this.doubleLinkedList.delete(value);
                        break;
                    default:
                        break;
                }
                if (this.doubleLinkedList.root != null) {
                    valuePanel.getButtonIntInput().setEnabled(false);
                } else {
                    valuePanel.getButtonIntInput().setEnabled(true);
                }
                break;
            case DYNAMICARRAYSTRING:
                switch (this.mode) {
                    case createString:
                        this.doubleDynamicArray.create(value, count);
                        break;
                    case readString:
                        this.readDynamicArray(this.doubleDynamicArray.read());
                        break;
                    case updateString:
                        this.doubleDynamicArray.update(value, count);
                        break;
                    case deleteString:
                        this.doubleDynamicArray.delete(count);
                        break;
                    default:
                        break;
                }
                if (this.doubleDynamicArray == null) {
                	valuePanel.getButtonDoubleInput().setEnabled(true);
                } else if (this.doubleDynamicArray.arr.length > 0) {
                    valuePanel.getButtonIntInput().setEnabled(false);
                } else {
                    valuePanel.getButtonIntInput().setEnabled(true);
                }
                break;
            case QUEUESTRING:
                switch (this.mode) {
                    case createString:
                        this.doubleQueue.create(value);
                        break;
                    case readString:
                        readDoubleQueueStack(this.doubleQueue.read(), this.doubleQueue.getLength());
                        break;
                    case deleteString:
                        this.doubleQueue.delete();
                        break;
                    default:
                        break;
                }
                if (this.doubleQueue.head != null && this.doubleQueue.tail != null) {
                    valuePanel.getButtonIntInput().setEnabled(false);
                } else {
                    valuePanel.getButtonIntInput().setEnabled(true);
                }
                break;
            case STACKSTRING:
                switch (this.mode) {
                    case createString:
                        this.doubleStack.create(value);
                        break;
                    case readString:
                        readDoubleQueueStack(this.doubleStack.read(), this.doubleStack.getLength());
                        break;
                    case deleteString:
                        this.doubleStack.delete();
                        break;
                    default:
                        break;
                }
                if (this.doubleStack.top != null) {
                    valuePanel.getButtonIntInput().setEnabled(false);
                } else {
                    valuePanel.getButtonIntInput().setEnabled(true);
                }
                break;
            case BSTSTRING:
                switch (this.mode) {
                    case createString:
                        this.doubleBST.create(value, count);
                        break;
                    case readString:
                        readDoubleNode(this.doubleBST.readValue(), this.doubleBST.readCount());
                        break;
                    case updateString:
                        this.doubleBST.update(value, count);
                        break;
                    case deleteString:
                        this.doubleBST.delete(value);
                        break;
                    default:
                        break;
                }
                if (this.doubleBST.root != null) {
                    valuePanel.getButtonIntInput().setEnabled(false);
                } else {
                    valuePanel.getButtonIntInput().setEnabled(true);
                }
                break;
            default:
                break;
        }
    }

    public void runDataStructure (Integer vertice1, Integer vertice2, Integer weight, String direction) {
        switch (this.dataStructure) {
            case GRAPHMATRIXSTRING:
                switch (this.mode) {
                    case createString:
                    case updateString:
                        this.intGraphMatrix.editEdge(vertice1, vertice2, weight, direction);
                        break;
                    case readString:
                        readIntMatrix(this.intGraphMatrix.readMatrix(), this.intGraphMatrix.readVertices());
                        break;
                    case deleteString:
                        this.intGraphMatrix.editEdge(vertice1, vertice2, 0, direction);
                        break;
                    default:
                        break;
                }
                break;
            }
        }

    public void readIntNode (ArrayList<ArrayList<Integer>> nodeArray) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<nodeArray.size(); i++) {
            readPanel.getListModel().addElement(String.format(
                "%d. Value: %d, Count: %d",
                i+1,
                nodeArray.get(i).get(0),
                nodeArray.get(i).get(1)
            ));
        }
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }
    public void readDoubleNode (ArrayList<Double> valueArr, ArrayList<Integer> countArr) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<valueArr.size(); i++) {
            readPanel.getListModel().addElement(String.format(
                "%d. Value: %.3f, Count: %d",
                i+1,
                valueArr.get(i),
                countArr.get(i)
            ));
        }
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }

    public void readDynamicArray (Integer[] valueArr) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<valueArr.length; i++) {
            readPanel.getListModel().addElement(String.format("%d. Value: %d", i, valueArr[i]));
        }
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }
    public void readDynamicArray (Double[] valueArr) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<valueArr.length; i++) {
            readPanel.getListModel().addElement(String.format("%d. Value: %.3f", i, valueArr[i]));
        }
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }

    public void readIntQueueStack (ArrayList<Integer> valueArr, Integer length) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<valueArr.size(); i++) {
            readPanel.getListModel().addElement(String.format("%d. Value: %d", i+1, valueArr.get(i)));
        }
        readPanel.getListModel().addElement(String.format("Length: %d", length));
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }
    public void readDoubleQueueStack (ArrayList<Double> valueArr, Integer length) {
        readPanel.getListModel().removeAllElements();
        for (int i=0; i<valueArr.size(); i++) {
            readPanel.getListModel().addElement(String.format("%d. Value: %.3f", i+1, valueArr.get(i)));
        }
        readPanel.getListModel().addElement(String.format("Length: %d", length));
        readPanel.getReadPane().revalidate();
        readPanel.getReadPane().repaint();
    }

    public void readIntMatrix (Integer[][] matrix, Integer[] vertices) {
        readPanel.tableModel.setRowCount(0);
        readPanel.tableModel.setColumnCount(0);
        if (vertices.length == 0) {
            return;
        }
        Renderer cellRenderer = new Renderer(readPanel.table.getTableHeader().getBackground());
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        readPanel.tableModel.addColumn("Vertex", vertices);
        for (int i=0; i<matrix.length; i++) {
            readPanel.tableModel.addColumn(vertices[i], matrix[i]);
        }
        readPanel.table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        readPanel.table.setRowHeight(20);
        readPanel.table.getTableHeader().setReorderingAllowed(false);
	    readPanel.setVisible(true);
    }

    class Renderer extends DefaultTableCellRenderer {
        Color backgroundColor;
        public Renderer(Color backgroundColor) {
          super();
          this.backgroundColor = backgroundColor;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setBackground(backgroundColor);
            return cell;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Make the window wide enough for the tabs to stay in one row
        Dimension size = super.getPreferredSize();
        size.width += extraWindowWidth;
        return size;
    }
}

class ValuePanel extends JPanel {
    public JLabel valueLabel;
    public JPanel panelInputType;
    public JRadioButton buttonIntInput;
    public JRadioButton buttonDoubleInput;
    public JRadioButton buttonDirection;
    public ButtonGroup inputGroup;
    // JRadioButton buttonStrInput;
    // JRadioButton buttonBoolInput;
    public JSpinner intSpinner;
    public JSpinner doubleSpinner;
    // JTextField stringInputField;
    // JRadioButton buttonTrue;
    // JRadioButton buttonFalse;
    // JPanel panelBool;

    public ValuePanel (String dataStructure) {
        // Panel to hold hidden parts
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        valueLabel = new JLabel();
        valueLabel.setFont(new Font("Arial", Font.BOLD, 12));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setForeground(Color.black);
        this.add(valueLabel);

        String intString = "Integer";
        String doubleString = "Double";
        // String strString = "String";
        // String boolString = "Boolean";

        // Create radio buttons
        switch (dataStructure) {
            case GRAPHMATRIXSTRING:
                buttonIntInput = new JRadioButton("Vertex");
                buttonIntInput.setActionCommand(intString);
                break;
            case "Direction":
                buttonIntInput = new JRadioButton("A => B");
                buttonIntInput.setActionCommand("A => B");
                break;
            default:
                buttonIntInput = new JRadioButton(intString);
                buttonIntInput.setActionCommand(intString);
                break;
        }
        buttonIntInput.setFocusable(false);
        buttonIntInput.setSelected(true);

        switch (dataStructure) {
            case GRAPHMATRIXSTRING:
                buttonDoubleInput = new JRadioButton("Edge");
                buttonDoubleInput.setActionCommand(doubleString);
                break;
            case "Direction":
                buttonDoubleInput = new JRadioButton("B => A");
                buttonDoubleInput.setActionCommand("B => A");
                buttonDirection = new JRadioButton("A <=> B");
                buttonDirection.setActionCommand("A <=> B");
                buttonDirection.setFocusable(false);
                break;
            default:
                buttonDoubleInput = new JRadioButton(doubleString);
                buttonDoubleInput.setActionCommand(doubleString);
                break;
        }
        buttonDoubleInput.setFocusable(false);

        // buttonStrInput = new JRadioButton(strString);
        // buttonStrInput.setActionCommand(strString);
        // buttonStrInput.setFocusable(false);

        // buttonBoolInput = new JRadioButton(boolString);
        // buttonBoolInput.setActionCommand(boolString);
        // buttonBoolInput.setFocusable(false);

        // Group radio buttons
        inputGroup = new ButtonGroup();
        inputGroup.add(buttonIntInput);
        inputGroup.add(buttonDoubleInput);
        if (dataStructure == "Direction") {
            inputGroup.add(buttonDirection);
        }
        // inputGroup.add(buttonStrInput);
        // inputGroup.add(buttonBoolInput);

        // Add buttons to panel
        panelInputType = new JPanel();
        panelInputType.setLayout(new FlowLayout());
        panelInputType.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panelInputType.setAlignmentX(Component.CENTER_ALIGNMENT); // for main panel's BoxLayout
        panelInputType.setAlignmentY(JPanel.TOP_ALIGNMENT);
        panelInputType.setPreferredSize(new Dimension(300, 40));
        panelInputType.setMaximumSize(panelInputType.getPreferredSize());
        panelInputType.add(buttonIntInput);
        panelInputType.add(buttonDoubleInput);
        if (dataStructure == "Direction") {
            panelInputType.add(buttonDirection);
        }
        // panelInputType.add(buttonStrInput);
        // panelInputType.add(buttonBoolInput);

        this.add(panelInputType);

        // Integer input field
        intSpinner = new JSpinner();
        intSpinner.setModel(new SpinnerNumberModel(0, -2147483648, 2147483647, 1));
        intSpinner.setEditor(new JSpinner.NumberEditor(intSpinner,"#"));
        intSpinner.setPreferredSize(new Dimension(200, 20));
        intSpinner.setMaximumSize(intSpinner.getPreferredSize());
        this.add(intSpinner);

        // Double input field
        doubleSpinner = new JSpinner();
        doubleSpinner.setModel(new SpinnerNumberModel(0, -1.23e+300, 1.23e+300, 0.001));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) doubleSpinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        doubleSpinner.setPreferredSize(new Dimension(200, 20));
        doubleSpinner.setMaximumSize(doubleSpinner.getPreferredSize());
        
        doubleSpinner.setAlignmentY(JPanel.TOP_ALIGNMENT);
        this.add(doubleSpinner);
        doubleSpinner.setVisible(false);

        // // String input field
        // stringInputField = new JTextField(); 
        // stringInputField.setPreferredSize(new Dimension(200, 20));
        // stringInputField.setMaximumSize(stringInputField.getPreferredSize());
        // this.add(stringInputField);
        // stringInputField.setVisible(false);

        // // Boolean input field
        // String trueString = "True";
        // String falseString = "False";
        // // Create radio buttons
        // buttonTrue = new JRadioButton(trueString);
        // buttonTrue.setActionCommand(trueString);
        // buttonTrue.setSelected(true);
        // buttonTrue.setFocusable(false);
        // buttonFalse = new JRadioButton(falseString);
        // buttonFalse.setActionCommand(falseString);
        // buttonFalse.setFocusable(false);
        // // Group radio buttons
        // ButtonGroup boolGroup = new ButtonGroup();
        // boolGroup.add(buttonTrue);
        // boolGroup.add(buttonFalse);
        // // Add buttons to panel
        // panelBool = new JPanel();
        // panelBool.setLayout(new FlowLayout());
        // panelBool.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        // panelBool.setAlignmentX(Component.CENTER_ALIGNMENT); // for main panel's BoxLayout
        // panelBool.setPreferredSize(new Dimension(200, 30));
        // panelBool.setMaximumSize(panelBool.getPreferredSize());
        // panelBool.add(buttonTrue);
        // panelBool.add(buttonFalse);
        // // Add elements to valuePanel
        // this.add(panelBool);
        // panelBool.setVisible(false);
    }

    public JLabel getValueLabel () {
        return valueLabel;
    }
    public JRadioButton getButtonIntInput () {
        return buttonIntInput;
    }
    public JRadioButton getButtonDoubleInput () {
        return buttonDoubleInput;
    }
    public JRadioButton getButtonDirection () {
        return buttonDirection;
    }
    public ButtonGroup getInputGroup () {
        return inputGroup;
    }
    public JSpinner getIntSpinner () {
        return intSpinner;
    }
    public JSpinner getDoubleSpinner () {
        return doubleSpinner;
    }
    public JPanel getPanelInputType () {
        return panelInputType;
    }
}

class CountPanel extends JPanel {
    public JLabel countLabel;
    public JSpinner countInputField;

    public CountPanel (String dataStructure) {
        this.setPreferredSize(new Dimension(200, 20));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        countLabel = new JLabel();
        countLabel.setFont(new Font("Arial", Font.BOLD, 12));
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countLabel.setForeground(Color.black);
        this.add(countLabel);

        countInputField = new JSpinner();
        switch (dataStructure) {
	        case DYNAMICARRAYSTRING:
	        	countInputField.setModel(new SpinnerNumberModel(0, 0, 2147483647, 1));
	        	break;
	        default:
	        	countInputField.setModel(new SpinnerNumberModel(1, 1, 2147483647, 1));
	        	break;
        }
        countInputField.setEditor(new JSpinner.NumberEditor(countInputField,"#"));
        countInputField.setPreferredSize(new Dimension(200, 20));
        countInputField.setMaximumSize(countInputField.getPreferredSize());
        this.add(countInputField);
    }

    public JLabel getCountLabel () {
        return countLabel;
    }

    public JSpinner getCountInputField () {
        return countInputField;
    }
}

class ReadPanel extends JPanel {
	public DefaultListModel<String> listModel;
	public JList<String> readNodeList;
	public DefaultTableModel tableModel;
	public JTable table;
	public JScrollPane readPane;

	public ReadPanel(String dataStructure) {
		super();
        this.setPreferredSize(new Dimension(200, 200));
        this.setLayout(new BorderLayout());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Scrollpanel to add nodes
        if (dataStructure == GRAPHMATRIXSTRING) {
        	tableModel = new DefaultTableModel();
        	table = new JTable(tableModel) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      //   	Font font = new Font("Verdana", Font.PLAIN, 12);
		    // table.setFont(font);
        	readPane = new JScrollPane(table);
        	table.setFillsViewportHeight(true);
		    table.setRowHeight(30);
		    readPane.setViewportView(table);
		    readPane.setVisible(true);
        } else {
        	listModel = new DefaultListModel<String>();
            readNodeList = new JList<String>(listModel);
            readNodeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            readNodeList.setLayoutOrientation(JList.VERTICAL);
            readNodeList.setVisibleRowCount(-1);
        	readPane = new JScrollPane(readNodeList);
        }

        this.add(readPane, BorderLayout.CENTER);
	}

	public DefaultListModel<String> getListModel () {
        return listModel;
    }

    public JList<String> getReadNodeList () {
        return readNodeList;
    }

    public DefaultTableModel getTableModel () {
        return tableModel;
    }

    // public void setTable (Integer[][] matrix, Integer[] vertices) {
    //     tableModel = new DefaultTableModel(matrix, vertices);
    //     table = new JTable(tableModel);
    //     Font font = new Font("Verdana", Font.PLAIN, 12);
	   //  table.setFont(font);
	   //  table.setRowHeight(30);
    //     readPane.revalidate();
    //     readPane.repaint();
    // }

    public JTable getTable () {
        return table;
    }

    public JScrollPane getReadPane () {
        return readPane;
    }
}