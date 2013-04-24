package sqltool;

import javax.swing.JFrame;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FormQuery form = new FormQuery();
		form.setSize(500, 400);
				
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Ferramenta para acessar Banco de dados");
		f.setSize(500, 500);
		f.add("Center", form);
		f.setVisible(true);
		
	}
}
