package loja.view;

import loja.model.bean.Funcionario;
import loja.model.dao.FuncionarioDAO;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner e = new Scanner( System.in );
        FuncionarioDAO funcdao = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        int inp = 0;
        String entrada;
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm");

        do {
            inp = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Registrar, " +
                    "2 - Visualizar registro, \n3 - Atualizar registro, 4 - Excluir registro, 6 -  sair"));

            switch (inp) {
                case 1:
                    entrada = JOptionPane.showInputDialog(null,"Digite o CPF do funcionario");
                    funcionario.setCpf(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite a matricula do funcionario");
                    funcionario.setMatricula(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o nome do funcionario");
                    funcionario.setNome(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o email do funcionario");
                    funcionario.setEmail(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o telefone do funcionario");
                    funcionario.setTelefone(entrada);

                    funcdao.salva(funcionario);
                    break;
                case 2:
                    System.out.println("-------------------------"+dtf3.format(LocalDateTime.now()));
                    for(Funcionario f : funcdao.getFucionario()){
                        System.out.println("-------------------------------------");
                        System.out.println("ID: "+f.getId()+", CPF: "+f.getCpf()+", matricula: "+f.getMatricula());
                        System.out.println("Nome: "+f.getNome()+", Email: "+f.getEmail()+", Telefone: "+f.getTelefone());
                    }

                    break;
                case 3:
                    entrada = JOptionPane.showInputDialog(null,"Digite o novo CPF do funcionario");
                    funcionario.setCpf(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite a nova matricula do funcionario");
                    funcionario.setMatricula(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o novo nome do funcionario");
                    funcionario.setNome(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o novo email do funcionario");
                    funcionario.setEmail(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o novo telefone do funcionario");
                    funcionario.setTelefone(entrada);
                    entrada = JOptionPane.showInputDialog(null,"Digite o ID do funcionario para altera!");
                    funcionario.setId(Integer.parseInt(entrada));
                    funcdao.updade(funcionario);
                    break;
                case 4:
                    funcdao.delete(Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione o id do registro que deseja excluir")));
                    break;
            }
        }while (inp != 6);

    }
}
