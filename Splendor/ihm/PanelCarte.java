package Splendor.ihm;

import Splendor.metier.*;
import Splendor.Controleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelCarte extends JPanel
{
	private Controleur ctrl;
	private ArrayList<Territoire> tabTerritoires;

	public PanelCarte(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabTerritoires = this.ctrl.getTerritoire();
		
		this.setPreferredSize(new Dimension(2000, 1500));
		this.setBackground(Color.WHITE);
	}

	
 	public void majIHM()
	{
		super.repaint();
	}

	public void paint(Graphics g)
	{
		final int RATIO_X = 30;
		final int RATIO_Y = 45;

		super.paint(g);
		Graphics2D g2D = (Graphics2D)(g);

		for (int i = 0; i < this.tabTerritoires.size(); i++)
		{
			for (int j = 0; j < this.tabTerritoires.get(i).getTabPoint().size(); j++)
			{
				g2D.setStroke(new BasicStroke((float)(2)));
				
				Point point = this.tabTerritoires.get(i).getTabPoint().get(j);
				Polygon hexagone = this.createHexagon((int)point.getX(), (int)point.getY());
				g2D.setColor(this.tabTerritoires.get(i).getCouleur());
				g2D.fillPolygon(hexagone);
				g2D.setColor(Color.BLACK);
				g2D.drawPolygon(hexagone);

				if (this.tabTerritoires.get(i).getPosseder() != null)
				{

					g2D.setColor(Color.BLACK); // Croix noir
					g2D.setStroke(new BasicStroke((float)(8)));
					g2D.drawLine((int)point.getX() * RATIO_X - 10, (int)point.getY() * RATIO_Y - 10, (int)point.getX() * RATIO_X + 10, (int)point.getY() * RATIO_Y + 10);
					g2D.drawLine((int)point.getX() * RATIO_X - 10, (int)point.getY() * RATIO_Y + 10, (int)point.getX() * RATIO_X + 10, (int)point.getY() * RATIO_Y - 10);


					g2D.setColor(this.tabTerritoires.get(i).getPosseder().getCouleur()); // Croix de la couleur du joueur possedant le territoire
					g2D.setStroke(new BasicStroke((float)(5)));
					g2D.drawLine((int)point.getX() * RATIO_X - 10, (int)point.getY() * RATIO_Y - 10, (int)point.getX() * RATIO_X + 10, (int)point.getY() * RATIO_Y + 10);
					g2D.drawLine((int)point.getX() * RATIO_X - 10, (int)point.getY() * RATIO_Y + 10, (int)point.getX() * RATIO_X + 10, (int)point.getY() * RATIO_Y - 10);
				}
			}
		}

	}
	
	private Polygon createHexagon(int x, int y) 
	{
		final int RADIUS = 30;
		final int RATIO_X = 30;
		final int RATIO_Y = 45;

		Polygon polygon = new Polygon();

		polygon.addPoint(x * RATIO_X - RADIUS  , y * RATIO_Y - RADIUS/2);
		polygon.addPoint(x * RATIO_X           , y * RATIO_Y - RADIUS  );
		polygon.addPoint(x * RATIO_X + RADIUS  , y * RATIO_Y - RADIUS/2);
		polygon.addPoint(x * RATIO_X + RADIUS  , y * RATIO_Y + RADIUS/2);
		polygon.addPoint(x * RATIO_X           , y * RATIO_Y + RADIUS  );
		polygon.addPoint(x * RATIO_X - RADIUS  , y * RATIO_Y + RADIUS/2);

		return polygon;
	}
}
