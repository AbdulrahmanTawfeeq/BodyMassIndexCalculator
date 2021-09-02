package CourseTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainInterface extends JFrame {
	int interfaceWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	int interfaceHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	JLabel copyRight = new JLabel("All Right Reserver to KUST 2021");
	JPanel centerPanel = new JPanel();
	JButton calcBMI = new JButton("Calculate BMI");

	JLabel heightIn;
	JLabel weightIn;

	JTextField heightInput;
	JTextField weightInput;

	JLabel feedback;
	JRadioButton metersRadio;
	JRadioButton inchesRadio;
	ButtonGroup buttonGroup = new ButtonGroup();

	public MainInterface() {
		mainInterfaceSettings();
		copyRightSettings();
		centerPanelSettings();
		setHeightInAndWieghtInLabels();
		setHeightAndWeightInputs();
		setFeedbackAndRadioBtns();
		calcButtonSettings();

		setVisible(true);
	}

	private void setFeedbackAndRadioBtns() {
		feedback = new JLabel("Feedback");
		feedBackStyle();
		metersRadio = new JRadioButton("Meters");
		inchesRadio = new JRadioButton("Inches");
		radiosEventHandling();
		buttonGroup.add(metersRadio);
		buttonGroup.add(inchesRadio);
		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		jPanel.add(feedback, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		JPanel radioPanel = new JPanel();
		radioPanel.add(metersRadio);
		radioPanel.add(inchesRadio);
		jPanel.add(radioPanel, gbc);
		centerPanel.add(jPanel);
	}

	private void radiosEventHandling() {
		inchesRadio.addActionListener((ActionEvent e) -> {
			heightIn.setText("Your Height in Inches: ");
			weightIn.setText("Your Weight in Pounds: ");
		});

		metersRadio.addActionListener((ActionEvent e) -> {
			heightIn.setText("Your Height in Meter: ");
			weightIn.setText("Your Weight in Kilograms: ");
		});
	}

	private void feedBackStyle() {
		feedback.setPreferredSize(new Dimension(150, 40));
		feedback.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		feedback.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	private void setHeightAndWeightInputs() {
		// TODO Auto-generated method stub
		heightInput = new JTextField();
		heightInput.setPreferredSize(new Dimension(70, 28));
		weightInput = new JTextField();
		weightInput.setPreferredSize(new Dimension(70, 28));
		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		jPanel.add(heightInput, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jPanel.add(weightInput, gbc);
		centerPanel.add(jPanel);
	}

	private void setHeightInAndWieghtInLabels() {
		heightIn = new JLabel("Your Height in Meter: ");
		weightIn = new JLabel("Your Wieght in Kilograms: ");
		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		jPanel.add(heightIn, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jPanel.add(weightIn, gbc);
		centerPanel.add(jPanel);
	}

	private void calcButtonSettings() {
		JPanel jPanel = new JPanel(new GridBagLayout());
		jPanel.add(calcBMI);
		centerPanel.add(jPanel);

		calcBMI.addActionListener((ActionEvent e) -> {
			if (metersRadio.isSelected()) {
				try {
					double heightInMeter = Double.parseDouble(heightInput.getText());
					double weightInKilo = Double.parseDouble(weightInput.getText());
					double bmi = kiloAndMeterFunctionality(heightInMeter, weightInKilo);
					check(bmi);
				} catch (NumberFormatException e1) {
					// in case of input a text instead of numeric
					JOptionPane.showMessageDialog(MainInterface.this, "Number Format is wrong for " + e1.getMessage());
				}

			} else if (inchesRadio.isSelected()) {
				try {
					double heightInInches = Double.parseDouble(heightInput.getText());
					double weightInPounds = Double.parseDouble(weightInput.getText());

					double bmi = inchesAndPoundsFunctionality(heightInInches, weightInPounds);
					check(bmi);
				} catch (NumberFormatException e1) {
					// in case of input a text instead of numeric
					JOptionPane.showMessageDialog(MainInterface.this, "Number Format is wrong for " + e1.getMessage());
				}
			} else {
				// if no options selected of the radio buttons
				JOptionPane.showMessageDialog(MainInterface.this,
						"Please select one of the options: Meters or Inches!");
			}
		});
	}

	/**
	 * 
	 * @param heightInMeter
	 * @param weightInKilo
	 * @return BMI
	 */
	private double kiloAndMeterFunctionality(double heightInMeter, double weightInKilo) {
		return weightInKilo / (heightInMeter * heightInMeter);
	}

	/**
	 * 
	 * @param heightInInches
	 * @param weightInPounds
	 * @return BMI
	 */
	private double inchesAndPoundsFunctionality(double heightInInches, double weightInPounds) {
		// 1meter = 39.370078 inches
		double heightInMeter = (1 * heightInInches) / 39.370078;
		// 1kilo = 2.20462 pounds
		double weightInKilo = (1 * weightInPounds) / 2.20462;
		return kiloAndMeterFunctionality(heightInMeter, weightInKilo);
	}

	private void check(double bmi) {
		if (bmi < 18.5) {
			feedback.setText("Underweighted");
		} else if (bmi >= 18.5 && bmi <= 24.9) {
			feedback.setText("Normal Weight");
		} else if (bmi >= 25 && bmi <= 29.9) {
			feedback.setText("Overweihted");
		} else if (bmi >= 30) {
			feedback.setText("Obesity");
		}
	}

	private void centerPanelSettings() {
		centerPanel.setLayout(new GridLayout(0, 4));
		add(centerPanel, BorderLayout.CENTER);
	}

	private void copyRightSettings() {
		copyRight.setHorizontalAlignment(SwingConstants.CENTER);
		copyRight.setOpaque(true);
		copyRight.setBackground(Color.GRAY);
		add(copyRight, BorderLayout.SOUTH);
	}

	/**
	 * Any settings related to the main frame
	 */
	private void mainInterfaceSettings() {
		setTitle("Body Mass Index Calculator");
		setLayout(new BorderLayout());
		setLocation(new Point(interfaceWidth / 4, interfaceHeight / 4));
		setSize(new Dimension(interfaceWidth / 2, interfaceHeight / 3));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(MainInterface.this,
						"Are you sure that you want to close the application?");
				if (response == JOptionPane.YES_OPTION) {
					dispose();
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainInterface();

	}

}
