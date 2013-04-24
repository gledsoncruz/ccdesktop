package sqltool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


public class FormQuery extends JPanel implements ActionListener {

	protected JLabel labelTitulo;

	protected JLabel labelSql;

	protected JLabel labelResultado;

	protected JLabel labelMensagem;

	protected JTextArea txaQuery;

	protected JTextArea txaMensagem;

	private JButton buttonExecutar;

	private JButton buttonLimpar;

	private JTable tabela = null;

	private JScrollPane paneTabela = null;

	private JScrollPane paneQuery = null;

	private JScrollPane paneMensagem = null;

	private ResultSet result;

	private Acesso a;

	public FormQuery() {

		this.setLayout(null);

		labelTitulo = new JLabel("Ferramenta para executar Query");
		labelSql = new JLabel("Query SQL:");
		labelResultado = new JLabel("Resultado:");
		labelMensagem = new JLabel("Mensagens:");

		txaQuery = new JTextArea();
		paneQuery = new JScrollPane(txaQuery,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		txaMensagem = new JTextArea();
		paneMensagem = new JScrollPane(txaMensagem,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		buttonExecutar = new JButton("Executar");
		buttonLimpar = new JButton("Limpar");

		buttonExecutar.addActionListener(this);
		buttonLimpar.addActionListener(this);

		labelTitulo.setBounds(160, 10, 250, 20);

		labelSql.setBounds(20, 60, 80, 20);
		paneQuery.setBounds(20, 80, 450, 80);

		labelMensagem.setBounds(20, 160, 80, 20);
		paneMensagem.setBounds(20, 180, 450, 40);

		labelResultado.setBounds(20, 220, 80, 20);

		buttonExecutar.setBounds(120, 430, 100, 20);
		buttonLimpar.setBounds(250, 430, 100, 20);

		this.add(labelTitulo);
		this.add(labelSql);
		this.add(paneQuery);
		this.add(labelMensagem);
		this.add(labelResultado);
		this.add(buttonExecutar);
		this.add(buttonLimpar);

	}

	public void actionPerformed(ActionEvent ae) {

		String sql = txaQuery.getText();

		if (ae.getActionCommand().equals("Executar")) {

			if (sql == null || sql.equals("")) {

				JOptionPane.showMessageDialog(null, "Informe a query", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				Acesso a = null;
				try {

					a = new Acesso();
					if (sql.toUpperCase().startsWith("SELECT")) {

						result = a.consultar(sql);
						Modelo m = new Modelo(result);

						tabela = new JTable(m);
						tabela.getSelectionModel().setSelectionMode(
								ListSelectionModel.SINGLE_SELECTION);
						paneTabela = new JScrollPane(tabela);
						paneTabela.setBounds(20, 250, 445, 160);
						paneTabela.setEnabled(false);
						this.add(paneTabela);
						this.close();

					} else {

						int res = a.atualizar(sql);
						this.add(paneMensagem);
						txaMensagem.setText("Linhas afetadas= "
								+ String.valueOf(res));
						this.close();
					}

				} catch (ClassNotFoundException e) {

					this.add(paneMensagem);
					txaMensagem.setText(e.getMessage());
					txaMensagem.append(" _ ");
					txaMensagem.append(e.getLocalizedMessage());
					this.close();

				} catch (SQLException e) {

					this.add(paneMensagem);
					txaMensagem.setText(e.getMessage());
					txaMensagem.append(" _ ");
					txaMensagem.append(e.getLocalizedMessage());
					this.close();
				}

			}

		} else {

			limpar();
		}

	}

	private void limpar() {
		txaQuery.setText("");
		txaMensagem.setText("");
		txaQuery.requestFocus();
	}

	public void iniciarFormulario() {
		txaQuery.requestFocus();
		getRootPane().setDefaultButton(buttonExecutar);
	}

	private void close() {

		try {
			if (a != null) {
				a.close();
			}

		} catch (ClassNotFoundException e) {
			txaMensagem.setText(e.getMessage());
			txaMensagem.append("_______________________");
			txaMensagem.append(e.getLocalizedMessage());
		} catch (SQLException e) {
			txaMensagem.setText(e.getMessage());
			txaMensagem.append("_______________________");
			txaMensagem.append(e.getLocalizedMessage());
		}

	}

}