package code;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
/**
 * This class builds and displays the user interface.
 * It also passes the input values to the appropriate methods
 * for calculation.  Exceptions are handled in this class
 * by pop-up notifications.
 * */
public class UI extends JFrame {
    /**Data instance for storing information*/
	private Data dataReference;
	/**Panel used for decimal input*/
	private JPanel decimalPanel;
	/**Panel for DegMinSec input (unimplemented)*/
	private JPanel degMinSecPanel;
	/**Latitude for datatReference.PointA*/
	private JTextField latAField;
	/**Longitude for datatReference.PointA*/
	private JTextField lonAField;
	/**Bearing for datatReference.PointA*/
	private JTextField bearAField;
	/**Latitude for datatReference.PointB*/
	private JTextField latBField;
	/**Longitude for datatReference.PointB*/
	private JTextField lonBField;
	/**Bearing for datatReference.PointB*/
	private JTextField bearBField;
	private JButton calculateButton;
	private JLabel lblPointOneLongitude;
	private JLabel lblPointTwoLongitude;
	private JLabel lblCurrentLatitudeoptional;
	private JLabel lblCurrentLongitudeoptional;
	private JTextField currentLatField;
	private JTextField currentLonField;
	private JLabel targetLatTitle;
	private JLabel lblTargetLongitude;
	private JLabel targetLatLabel;
	private JLabel targetLonLabel;
	private JLabel lblDistanceToTarget;
	private JLabel lblBearingToTarget;
	private JLabel targetDistLabel;
	private JLabel targetBearLabel;
	private JButton updateButton;

	/**
	 * Creates and displays an application window ready to accept decimal input.
	 * @param d Instance of {@link Data}} to store input.
	 */
	public UI(Data d) {
		dataReference = d;
		
		JPanel topPanel = new JPanel();
	    topPanel.setLayout( new BorderLayout() );
	    getContentPane().add( topPanel );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 435);
		
		createDecimal();
		topPanel.add(decimalPanel);
	}
	
	/**
	 * Creates a panel designed to accept decimal input
	 * */
	private void createDecimal(){
		decimalPanel = new JPanel();
		decimalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		decimalPanel.setLayout(null);
		decimalPanel.setVisible(true);
		
		lonAField = new JTextField();
		lonAField.setBounds(10, 127, 111, 20);
		decimalPanel.add(lonAField);
		lonAField.setColumns(10);
		
		latBField = new JTextField();
		latBField.setBounds(10, 201, 111, 20);
		decimalPanel.add(latBField);
		latBField.setColumns(10);
		
		bearAField = new JTextField();
		bearAField.setBounds(141, 72, 56, 20);
		decimalPanel.add(bearAField);
		bearAField.setColumns(10);
		
		bearBField = new JTextField();
		bearBField.setBounds(146, 201, 56, 20);
		decimalPanel.add(bearBField);
		bearBField.setColumns(10);
		
		JLabel lblPoint = new JLabel("Point One Latitude");
		lblPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPoint.setBounds(10, 43, 111, 18);
		decimalPanel.add(lblPoint);
		
		JLabel lblPoint_1 = new JLabel("Point Two Latitude");
		lblPoint_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPoint_1.setBounds(10, 170, 111, 20);
		decimalPanel.add(lblPoint_1);
		
		JLabel lblBearing = new JLabel("Bearing two");
		lblBearing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBearing.setBounds(146, 170, 72, 20);
		decimalPanel.add(lblBearing);
		
		JLabel label = new JLabel("Bearing one");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(141, 43, 72, 18);
		decimalPanel.add(label);
		
		latAField = new JTextField();
		latAField.setColumns(10);
		latAField.setBounds(10, 72, 111, 20);
		decimalPanel.add(latAField);
		
		lonBField = new JTextField();
		lonBField.setColumns(10);
		lonBField.setBounds(10, 257, 111, 20);
		decimalPanel.add(lonBField);
		
		calculateButton = new JButton("Calculate");
		calculateButton.setBounds(10, 288, 89, 23);
		decimalPanel.add(calculateButton);
		CalcListener c = new CalcListener();
		calculateButton.addActionListener(c);
		
		lblPointOneLongitude = new JLabel("Point One Longitude");
		lblPointOneLongitude.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPointOneLongitude.setBounds(10, 97, 145, 20);
		decimalPanel.add(lblPointOneLongitude);
		
		lblPointTwoLongitude = new JLabel("Point Two Longitude");
		lblPointTwoLongitude.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPointTwoLongitude.setBounds(10, 226, 133, 20);
		decimalPanel.add(lblPointTwoLongitude);
		
		lblCurrentLatitudeoptional = new JLabel("Current Latitude");
		lblCurrentLatitudeoptional.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurrentLatitudeoptional.setBounds(463, 44, 162, 16);
		decimalPanel.add(lblCurrentLatitudeoptional);
		
		lblCurrentLongitudeoptional = new JLabel("Current Longitude");
		lblCurrentLongitudeoptional.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurrentLongitudeoptional.setBounds(463, 97, 118, 20);
		decimalPanel.add(lblCurrentLongitudeoptional);
		
		currentLatField = new JTextField();
		currentLatField.setColumns(10);
		currentLatField.setBounds(463, 72, 111, 20);
		decimalPanel.add(currentLatField);
		
		currentLonField = new JTextField();
		currentLonField.setColumns(10);
		currentLonField.setBounds(463, 127, 111, 20);
		decimalPanel.add(currentLonField);
		
		targetLatTitle = new JLabel("Target Latitude");
		targetLatTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		targetLatTitle.setBounds(259, 38, 98, 29);
		decimalPanel.add(targetLatTitle);
		
		lblTargetLongitude = new JLabel("Target Longitude");
		lblTargetLongitude.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTargetLongitude.setBounds(259, 93, 98, 29);
		decimalPanel.add(lblTargetLongitude);
		
		targetLatLabel = new JLabel("");
		targetLatLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		targetLatLabel.setBounds(259, 72, 98, 20);
		decimalPanel.add(targetLatLabel);
		
		targetLonLabel = new JLabel("");
		targetLonLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		targetLonLabel.setBounds(259, 127, 98, 20);
		decimalPanel.add(targetLonLabel);
		
		lblDistanceToTarget = new JLabel("Distance to Target");
		lblDistanceToTarget.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDistanceToTarget.setBounds(463, 158, 133, 29);
		decimalPanel.add(lblDistanceToTarget);
		
		lblBearingToTarget = new JLabel("Bearing to Target");
		lblBearingToTarget.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBearingToTarget.setBounds(463, 217, 133, 29);
		decimalPanel.add(lblBearingToTarget);
		
		targetDistLabel = new JLabel("");
		targetDistLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		targetDistLabel.setBounds(463, 187, 98, 20);
		decimalPanel.add(targetDistLabel);
		
		targetBearLabel = new JLabel("");
		targetBearLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		targetBearLabel.setBounds(463, 244, 98, 20);
		decimalPanel.add(targetBearLabel);
		
		updateButton = new JButton("Update");
		updateButton.setBounds(463, 275, 89, 23);
		decimalPanel.add(updateButton);
		updateListener u = new updateListener();
		updateButton.addActionListener(u);
	}
	
	/**
	 * ActionListener for the Calculate button.
	 * {@link dataReference#pointA} and data {@link dataReference#pointB}
	 * are set.  The target is calculated and displayed.
	 * */
	private class CalcListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			try{
				dataReference.setPointA(Double.parseDouble(latAField.getText()),
                                        Double.parseDouble(lonAField.getText()),
			                            Double.parseDouble(bearAField.getText()));
				dataReference.setPointB(Double.parseDouble(latBField.getText()),
								        Double.parseDouble(lonBField.getText()),
			                            Double.parseDouble(bearBField.getText()));
				dataReference.calcTarget();
				targetLatLabel.setText(formatDouble6(dataReference.getTarget().getLatitude()));
				targetLonLabel.setText(formatDouble6(dataReference.getTarget().getLongitude()));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Only numerical inputs are accepted.");
			}catch(OutOfRangeException e){
				JOptionPane.showMessageDialog(null, e.getError());
			}
		}
	}
	/**
	 * ActionListener for the Update button.
	 * The distance and bearing to the target are updated
	 * and displayed.
	 */
	private class updateListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			try{
				if(dataReference.getTarget() == null){
					throw new OutOfRangeException("Target must be set before updating distance to target and bearing to target.");
				}
				dataReference.setCurrent(Double.parseDouble(currentLatField.getText()),
					                     Double.parseDouble(currentLonField.getText()));
				targetDistLabel.setText(Double.toString(dataReference.getTarget().distanceTo(dataReference.getCurrent())) + " meters");
				targetBearLabel.setText(Double.toString((dataReference.getTarget().bearingTo(dataReference.getCurrent()))));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Only numerical inputs are accepted.");
			}catch(OutOfRangeException e){
				JOptionPane.showMessageDialog(null, e.getError());
			}
		}
	}
	/**
	 * Used to truncate doubles to 6 places for display.
	 * @param d The double to be truncated.
	 * @return String with a double rounded to 6 places
	 */
	private String formatDouble6(double d){
		DecimalFormat sixPlaces = new DecimalFormat("#.######");
		return sixPlaces.format(d);
	}
}
