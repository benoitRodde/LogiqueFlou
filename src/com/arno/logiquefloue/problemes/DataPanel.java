package com.arno.logiquefloue.problemes;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.arno.logiquefloue.beans.FuzzySystem;
import com.arno.logiquefloue.beans.LeftFuzzySet;
import com.arno.logiquefloue.beans.LinguisticValue;
import com.arno.logiquefloue.beans.LinguisticVariable;
import com.arno.logiquefloue.beans.RightFuzzySet;
import com.arno.logiquefloue.beans.TrapezoidalFuzzySet;

public class DataPanel extends JPanel {
	public DataPanel() {
		// Création du système
				FuzzySystem system=new FuzzySystem("Gestion du Zoom GPS");
				
				// ajout des variables
		LinguisticVariable distance=new LinguisticVariable("Distance", 0, 500000);
		distance.add(new LinguisticValue("Faible", new LeftFuzzySet(0, 500000, 30, 50)));
		distance.add(new LinguisticValue("Moyenne", new TrapezoidalFuzzySet(0, 500000, 40, 50, 100, 150)));
		distance.add(new LinguisticValue("Grande", new RightFuzzySet(0, 500000, 100, 150)));
		system.addInputVariable(distance);
		
		LinguisticVariable vitesse=new LinguisticVariable("Vitesse", 0, 200);
		vitesse.add(new LinguisticValue("Lente", new LeftFuzzySet(0, 200, 20, 30)));
		vitesse.add(new LinguisticValue("PeuRapide", new TrapezoidalFuzzySet(0, 200, 20, 30, 70, 80)));
		vitesse.add(new LinguisticValue("Rapide", new TrapezoidalFuzzySet(0, 200, 70, 80, 90, 110)));
		vitesse.add(new LinguisticValue("TresRapide", new RightFuzzySet(0, 200, 90, 110)));
		system.addInputVariable(vitesse);
			
		LinguisticVariable zoom=new LinguisticVariable("Zoom", 0, 5);
		zoom.add(new LinguisticValue("Petit", new LeftFuzzySet(0, 5, 1, 2)));
		zoom.add(new LinguisticValue("Normal", new TrapezoidalFuzzySet(0, 5, 1, 2, 3, 4)));
		zoom.add(new LinguisticValue("Gros", new RightFuzzySet(0, 5, 3, 4)));
		system.addOutputVariable(zoom);
		
		// ajout des règles
		try {
			SAXBuilder sxb=new SAXBuilder();
			Document doc=sxb.build(new File("gps.xml"));
			List<Element> lrules=doc.getRootElement().getChild("rules").getChildren();
			for (Element rule : lrules) {
				system.addFuzzyRule(rule);
			}
		} catch(Exception exc) {
			System.err.println(exc.getMessage());
			System.exit(1);
		}
				
		setLayout(new GridLayout(1, 2));
		
		JLabel lblDistance = new JLabel("Distance : ");
		add(lblDistance);
		
		JTextField txtFieldDistance = new JTextField(6);
		add(txtFieldDistance);
		
		JLabel lblVitesse = new JLabel("Vitesse : ");
		add(lblVitesse);
		
		JTextField txtFieldVitesse = new JTextField(6);
		add(txtFieldVitesse);

		Button btnGo = new Button("Go");
		add(btnGo);
		
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				System.out.println(txtFieldVitesse.getText());
				System.out.println(txtFieldDistance.getText());
				system.resetCase();
				system.setInputVariable(vitesse, Double.parseDouble(txtFieldVitesse.getText()));
				system.setInputVariable(distance, Double.parseDouble(txtFieldDistance.getText()));
				System.out.format("Résultat : %.3f\n",system.solve());
				} catch(Exception exc) {
					System.err.println(exc.getMessage());
				}
			}
		});
	}
}
