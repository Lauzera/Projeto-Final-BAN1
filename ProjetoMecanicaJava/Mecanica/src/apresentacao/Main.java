package apresentacao;
import dados.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        try{
            Sistema sistema = new Sistema("udesc");
            sistema.carregarDados();
            System.out.println("Sistema iniciado com sucesso!");

            System.out.println("Digite os dados da oficina:");
            Scanner scanner = new Scanner(System.in);
            String nome = scanner.nextLine();
            String cnpj = scanner.nextLine();
            String telefone = scanner.nextLine();

            Oficina o = new Oficina(nome, cnpj, telefone);
            //sistema.inserirOficina(o);
            System.out.println("\n" + o.toString());
            int opcao;

            do{
                sistema.menu();
                opcao = scanner.nextInt();
                switch(opcao){
                
                case 1:
                
                    sistema.opcoes();
                    int opcao2 = scanner.nextInt();
                    scanner.nextLine();
                
                if(opcao2 == 1){
                    
                    System.out.println("Digite os dados do cliente(nome, telefone):");
                    String nomeCliente = scanner.nextLine();
                    String telefoneCliente = scanner.nextLine();
                    Cliente c = new Cliente(nomeCliente, telefoneCliente);
                    sistema.adicionarCliente(c);
                    sistema.inserirCliente(c);
                    System.out.println("Cliente adicionado com sucesso!");

                } else if(opcao2 == 2){
                    
                    if(sistema.getClientes().isEmpty()){
                        System.out.println("Não há clientes cadastrados para deletar!");
                        break;
                    }

                    System.out.println("Digite o ID do cliente a ser deletado:");
                    for(Cliente cliente: sistema.getClientes()){
                        System.out.println(cliente.getId() + " - " + cliente.getNome());
                    }
                    int idDeletar = scanner.nextInt();
                    
                    Cliente clienteRemover = null;
                    for(Cliente cliente: sistema.getClientes()){
                        if(cliente.getId() == idDeletar){
                            clienteRemover = cliente;
                            break;
                        }
                    }
                        if(clienteRemover != null){
                            sistema.removerCliente(clienteRemover);
                            sistema.deletarCliente(clienteRemover);
                            System.out.println("Cliente deletado com sucesso!");
                        }

                } else if(opcao2 == 3){
                    
                    if(sistema.getClientes().isEmpty()){
                        System.out.println("Não há clientes cadastrados para atualizar!");
                        break;
                    }

                    System.out.println("Digite o ID do cliente a ser atualizado:");
                    for(Cliente cliente: sistema.getClientes()){
                        System.out.println(cliente.getId() + " - " + cliente.getNome());
                    }
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    for(Cliente cliente: sistema.getClientes()){
                        if(cliente.getId() == idAtualizar){
                            System.out.println("Digite os novos dados do cliente(nome, telefone):");
                            String nomeNovo = scanner.nextLine();
                            String telefoneNovo = scanner.nextLine();
                            sistema.atualizarCliente(cliente, nomeNovo, telefoneNovo);
                            if(!sistema.getVeiculos().isEmpty()){
                                for(Veiculo veiculo: sistema.getVeiculos()){
                                    if(veiculo.getCliente().equals(cliente)){
                                        veiculo.setCliente(cliente);
                                    }
                                }
                            }
                            System.out.println("Cliente atualizado com sucesso!");
                        }
                    }
                }
                    break;

                case 2:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine();
                
                if(opcao2 == 1){
                    
                    System.out.println("Digite os dados do fornecedor(nome, cnpj, telefone):");
                    String nomeFornecedor = scanner.nextLine();
                    String cnpjFornecedor = scanner.nextLine();
                    String telefoneFornecedor = scanner.nextLine();
                    Fornecedor f = new Fornecedor(nomeFornecedor, cnpjFornecedor, telefoneFornecedor);
                    sistema.inserirFornecedor(f);
                    sistema.adicionarFornecedor(f);
                    System.out.println("Fornecedor adicionado com sucesso!");

                } else if(opcao2 == 2){
                    
                    if(sistema.getFornecedores().isEmpty()){
                        System.out.println("Não há fornecedores cadastrados para deletar!");
                        break;
                    }

                    System.out.println("Digite o CNPJ do fornecedor a ser deletado:");
                    for(Fornecedor fornecedor: sistema.getFornecedores()){
                        System.out.println(fornecedor.getNome() + " - " + fornecedor.getCnpj());
                    }
                    String cnpjDeletar = scanner.nextLine();
                    
                    Fornecedor fornecedorRemover = null;
                    for(Fornecedor fornecedor: sistema.getFornecedores()){
                        if(fornecedor.getCnpj().equals(cnpjDeletar)){     
                            fornecedorRemover = fornecedor;
                            break;
                        }
                    }
                        if(fornecedorRemover != null){    
                            sistema.removerFornecedor(fornecedorRemover);
                            sistema.deletarFornecedor(fornecedorRemover);
                            System.out.println("Fornecedor deletado com sucesso!");
                        }

                } else if(opcao2 == 3){
                        
                    if(sistema.getFornecedores().isEmpty()){
                        System.out.println("Não há fornecedores cadastrados para atualizar!");
                        break;
                    }

                    System.out.println("Digite o CNPJ do fornecedor a ser atualizado:");
                            for(Fornecedor fornecedor: sistema.getFornecedores()){
                                System.out.println(fornecedor.getNome() + " - " + fornecedor.getCnpj());
                        }
                    String cnpjAtualizar = scanner.nextLine();
                    for(Fornecedor fornecedor: sistema.getFornecedores()){
                        if(fornecedor.getCnpj().equals(cnpjAtualizar)){
                            System.out.println("Digite os novos dados do fornecedor(nome, telefone):");
                            String nomeNovo = scanner.nextLine();
                            String telefoneNovo = scanner.nextLine();
                            sistema.atualizarFornecedor(fornecedor, nomeNovo, telefoneNovo);
                            System.out.println("Fornecedor atualizado com sucesso!");
                        }
                    }
                }
                    break;

                case 3:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine(); 

                if(opcao2 == 1){
                    
                    System.out.println("Digite os dados do mecânico(nome, cpf, salario):");
                    String nomeMecanico = scanner.nextLine();
                    String cpfMecanico = scanner.nextLine();
                    double salarioMecanico = scanner.nextDouble();
                    Mecanico m = new Mecanico(nomeMecanico, cpfMecanico, salarioMecanico, o);
                    sistema.inserirMecanico(m);
                    sistema.adicionarMecanico(m);
                    System.out.println("Mecanico adicionado com sucesso!");

                } else if(opcao2 == 2){
                   
                    if(sistema.getMecanicos().isEmpty()){
                        System.out.println("Não há mecanicos cadastrados para deletar!");
                        break;
                    }

                    System.out.println("Digite o CPF do Mecanico a ser deletado:");
                    for(Mecanico mecanico: sistema.getMecanicos()){
                        System.out.println(mecanico.getNome() + " - " + mecanico.getCpf());
                    }
                    String cpfDeletar = scanner.nextLine();
                    Mecanico mecanicoRemover = null;

                    for(Mecanico mecanico: sistema.getMecanicos()){
                        if(mecanico.getCpf().equals(cpfDeletar)){                    
                            mecanicoRemover = mecanico;
                            break;
                        }
                    }
                    
                    if(mecanicoRemover != null){
                        sistema.removerMecanico(mecanicoRemover);
                        sistema.deletarMecanico(mecanicoRemover);
                        System.out.println("Mecanico deletado com sucesso!");
                    }

                } else if(opcao2 == 3){
                    
                    if(sistema.getMecanicos().isEmpty()){
                        System.out.println("Não há mecanicos cadastrados para atualizar!");
                        break;
                    }

                    System.out.println("Digite o CPF do Mecanico a ser atualizado:");
                    for(Mecanico mecanico: sistema.getMecanicos()){
                        System.out.println(mecanico.getNome() + " - " + mecanico.getCpf());
                    }
                    String cpfAtualizar = scanner.nextLine();
                    for(Mecanico mecanico: sistema.getMecanicos()){
                        if(mecanico.getCpf().equals(cpfAtualizar)){
                            System.out.println("Digite os novos dados do mecânico(nome, salario):");
                            String nomeNovo = scanner.nextLine();
                            double salarioNovo = scanner.nextDouble();
                            scanner.nextLine(); 
                            sistema.atualizarMecanico(mecanico, nomeNovo, salarioNovo);
                            System.out.println("Mecânico atualizado com sucesso!");
                        }
                    }
                }
                    break;
                
                case 4:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine();
                
                if(opcao2 == 1){
                    
                    if(sistema.getClientes().isEmpty()){
                        System.out.println("Não há clientes cadastrados para adicionar veiculos!");
                        break;
                    }

                    System.out.println("Digite os dados do veículo(Placa, nome, cor):");
                    String placa = scanner.nextLine();
                    String nomeVeiculo = scanner.nextLine();
                    String cor = scanner.nextLine();
                    
                    for(Cliente cliente: sistema.getClientes()){
                        System.out.println(cliente.getId() + " - " + cliente.getNome());
                    }
                    System.out.println("Digite o ID do cliente dono do veículo:");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = sistema.buscarCliente(idCliente);
                    
                    if(cliente != null){
                        Veiculo veiculo = new Veiculo(nomeVeiculo, placa, cor, cliente);
                        sistema.inserirVeiculo(veiculo);
                        sistema.adicionarVeiculo(veiculo);
                        System.out.println("Veículo adicionado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }

                } else if(opcao2 == 2){
                    
                    if(sistema.getVeiculos().isEmpty()){
                        System.out.println("Não há veículos cadastrados para deletar!");
                        break;
                    }

                    for(Veiculo veiculo: sistema.getVeiculos()){
                        System.out.println(veiculo.getPlaca() + " - " + veiculo.getNome());
                    }
                    System.out.println("Digite a placa do veículo a ser deletado:");
                    String placaDeletar = scanner.nextLine();
                    Veiculo veiculoRemover = null;
                    
                    for(Veiculo veiculo: sistema.getVeiculos()){
                        if(veiculo.getPlaca().equals(placaDeletar)){
                            veiculoRemover = veiculo;
                            break;
                        }
                    }

                    if(veiculoRemover != null){
                        sistema.removerVeiculo(veiculoRemover);
                        sistema.deletarVeiculo(veiculoRemover);
                        System.out.println("Veiculo deletado com sucesso!");
                    }

                } else if(opcao2 == 3){
                    
                    if(sistema.getVeiculos().isEmpty()){
                        System.out.println("Não há veículos cadastrados para atualizar!");
                        break;
                    }

                    System.out.println("Digite a placa do veículo a ser atualizado:");
                    for(Veiculo veiculo: sistema.getVeiculos()){
                        System.out.println(veiculo.getPlaca() + " - " + veiculo.getNome());
                    }
                    String placaAtualizar = scanner.nextLine();
                    for(Veiculo veiculo: sistema.getVeiculos()){
                        if(veiculo.getPlaca().equals(placaAtualizar)){
                            System.out.println("Digite os novos dados do veículo(nome, cor):");
                            String nomeNovo = scanner.nextLine();
                            String corNova = scanner.nextLine();
                            sistema.atualizarVeiculo(veiculo, nomeNovo, corNova);
                            System.out.println("Veículo atualizado com sucesso!");
                        }
                    }
                }
                    break;

                case 5:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine(); 
                if(opcao2 == 1){
                    
                    if(sistema.getClientes().isEmpty() || sistema.getVeiculos().isEmpty()){
                        System.out.println("Não há clientes ou veículos cadastrados para adicionar serviços!");
                        break;
                    }

                    System.out.println("Selecione o cliente para o serviço:");
                    for(Cliente cliente: sistema.getClientes()){
                        System.out.println(cliente.getId() + " - " + cliente.getNome());
                    }
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cliente = sistema.buscarCliente(idCliente);
                    
                    System.out.println("Selecione o veículo para o serviço:");
                    for(Veiculo veiculo: sistema.getVeiculos()){
                        if(veiculo.getCliente().equals(cliente)){
                            System.out.println(veiculo.getPlaca() + " - " + veiculo.getNome());
                        }
                    }
                    String placaVeiculo = scanner.nextLine();
                    Veiculo veiculo = sistema.buscarVeiculo(placaVeiculo);

                    if(cliente != null && veiculo != null){
                        Servico servico = new Servico(o, cliente, veiculo);
                        sistema.inserirServico(servico);
                        sistema.adicionarServico(servico);
                        System.out.println("Serviço adicionado com sucesso!");
                    } else {
                        System.out.println("Cliente ou veículo não encontrado!");
                    }

                }else if(opcao2 == 2){
                    
                    if(sistema.getServicos().isEmpty()){
                        System.out.println("Não há serviços cadastrados para deletar!");
                        break;
                    }

                    for(Servico servico: sistema.getServicos()){
                        System.out.println(servico.getId() + " - " + servico.getCliente().getNome() + " - " + servico.getVeiculo().getPlaca());
                    }
                    System.out.println("Digite o ID do serviço a ser deletado:");
                    int idDeletar = scanner.nextInt();
                    Servico servicoRemover = null;
                    for(Servico servico: sistema.getServicos()){
                        if(servico.getId() == idDeletar){
                            servicoRemover = servico;
                        }
                    }

                    if(servicoRemover != null){
                        sistema.removerServico(servicoRemover);
                        sistema.deletarServico(servicoRemover);
                        System.out.println("Serviço deletado com sucesso!");
                    }
                
                } else if(opcao2 == 3){
                    
                    if(sistema.getServicos().isEmpty()){
                        System.out.println("Não há serviços cadastrados para atualizar!");
                        break;
                    }

                    System.out.println("Digite o ID do serviço a ser atualizado:");
                    for(Servico servico: sistema.getServicos()){
                        System.out.println(servico.getId() + " - " + servico.getCliente().getNome() + " - " + servico.getVeiculo().getPlaca());
                    }
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    for(Servico servico: sistema.getServicos()){
                        if(servico.getId() == idAtualizar){
                            System.out.println("Selecione o novo cliente para o serviço:");
                            for(Cliente cliente: sistema.getClientes()){
                                System.out.println(cliente.getId() + " - " + cliente.getNome());
                            }
                            int idClienteNovo = scanner.nextInt();
                            Cliente clienteNovo = sistema.buscarCliente(idClienteNovo);
                            System.out.println("Selecione o novo veículo para o serviço:");
                            for(Veiculo veiculo: sistema.getVeiculos()){
                                System.out.println(veiculo.getPlaca() + " - " + veiculo.getNome());
                            }
                            String placaVeiculoNovo = scanner.nextLine();
                            Veiculo veiculoNovo = sistema.buscarVeiculo(placaVeiculoNovo);

                            if(clienteNovo != null && veiculoNovo != null){
                                sistema.atualizarServico(servico, clienteNovo, veiculoNovo);
                                System.out.println("Serviço atualizado com sucesso!");
                            } else {
                                System.out.println("Cliente ou veículo não encontrado!");
                            }
                        }
                    }
                }
                    break;

                case 6:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine();
                
                if(opcao2 == 1){
                    
                    if(sistema.getServicos().isEmpty() || sistema.getMecanicos().isEmpty() || sistema.getVeiculos().isEmpty()){
                        System.out.println("Não há serviços, veículos ou mecânicos cadastrados para adicionar consertos!");
                        break;
                    }

                    System.out.println("Digite os dados do conserto:");
                    System.out.println("Selecione o serviço:");
                    for(Servico servico: sistema.getServicos()){
                        System.out.println(servico.getId() + " - " + servico.getCliente().getNome() + " - " + servico.getVeiculo().getPlaca());
                    }
                    int idServico = scanner.nextInt();
                    Servico servico = sistema.buscarServico(idServico);

                    System.out.println("Selecione o mecânico responsável pelo conserto:");
                    for(Mecanico mecanico: sistema.getMecanicos()){
                        System.out.println(mecanico.getCpf() + " - " + mecanico.getNome());
                    }
                    scanner.nextLine();
                    String cpfMecanico = scanner.nextLine();
                    Mecanico mecanico = sistema.buscarMecanico(cpfMecanico);
                    Veiculo veiculo = servico.getVeiculo();

                    System.out.println("Digite a descrição do conserto:");
                    String descricao = scanner.nextLine();
                    System.out.println("Digite o valor do conserto:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite a data de início do conserto (dd/mm/aaaa):");
                    String dataInicio = scanner.nextLine();
                    System.out.println("Digite a data de fim do conserto (dd/mm/aaaa):");
                    String dataFim = scanner.nextLine();

                    Conserto conserto = new Conserto(servico, mecanico, veiculo, descricao, valor, dataInicio, dataFim);
                    sistema.inserirConserto(conserto);
                    sistema.adicionarConserto(conserto);
                    System.out.println("Conserto adicionado com sucesso!");

                } else if(opcao2 == 2){
                    
                    for(Conserto conserto: sistema.getConsertos()){
                        System.out.println(conserto.getId() + " - " + conserto.getServico().getCliente().getNome() + " - " + conserto.getServico().getVeiculo().getPlaca());
                    }
                    System.out.println("Digite o ID do conserto a ser deletado:");
                    int idDeletar = scanner.nextInt();
                    
                    Conserto consertoDeletar = null;
                    for(Conserto conserto: sistema.getConsertos()){
                        if(conserto.getId() == idDeletar){
                            consertoDeletar = conserto;
                            break;
                        }
                    }

                    if(consertoDeletar != null){
                        sistema.removerConserto(consertoDeletar);
                        sistema.deletarConserto(consertoDeletar);
                        System.out.println("Conserto deletado com sucesso!");
                    }

                } else if(opcao2 == 3){
                    
                    System.out.println("Digite o ID do conserto a ser atualizado:");
                    for(Conserto conserto: sistema.getConsertos()){
                        System.out.println(conserto.getId() + " - " + conserto.getServico().getCliente().getNome() + " - " + conserto.getServico().getVeiculo().getPlaca());
                    }
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    for(Conserto conserto: sistema.getConsertos()){
                        if(conserto.getId() == idAtualizar){
                            System.out.println("Digite a nova descrição do conserto:");
                            String descricaoNova = scanner.nextLine();
                            System.out.println("Digite o novo valor do conserto:");
                            double valorNovo = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.println("Digite a nova data de início do conserto (dd/mm/aaaa):");
                            String dataInicioNova = scanner.nextLine();
                            System.out.println("Digite a nova data de fim do conserto (dd/mm/aaaa):");
                            String dataFimNova = scanner.nextLine();
                            scanner.nextLine(); 

                            sistema.atualizarConserto(conserto, descricaoNova, valorNovo, dataInicioNova, dataFimNova);
                            System.out.println("Conserto atualizado com sucesso!");
                        }
                    }
                }
                    break;
                
                case 7:
                    
                    sistema.opcoes();
                    opcao2 = scanner.nextInt();
                    scanner.nextLine(); 

                if(opcao2 == 1){
                    
                    if(sistema.getFornecedores().isEmpty()){
                        System.out.println("Não há fornecedores cadastrados para adicionar fornecimentos!");
                        break;
                    }
                    
                    System.out.println("Digite os dados do fornecimento:");
                    System.out.println("Selecione o fornecedor:");
                    for(Fornecedor fornecedor: sistema.getFornecedores()){
                        System.out.println(fornecedor.getCnpj() + " - " + fornecedor.getNome());
                    }
                    String cnpjFornecedor = scanner.nextLine();
                    Fornecedor fornecedor = sistema.buscarFornecedor(cnpjFornecedor);
                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = scanner.nextLine();
                    Fornecimento fornecimento = new Fornecimento(fornecedor, o, nomeProduto);
                    sistema.inserirFornecimento(fornecimento);
                    sistema.adicionarFornecimento(fornecimento);
                } else if(opcao2 == 2){
                    
                    for(Fornecimento fornecimento: sistema.getFornecimentos()){
                        System.out.println(fornecimento.getId() + " - " + fornecimento.getProduto());
                    }
                    System.out.println("Digite o ID do fornecimento a ser deletado:");
                    int idDeletar = scanner.nextInt();
                    Fornecimento fornecimentoDeletar = null;
                    
                    for(Fornecimento fornecimento: sistema.getFornecimentos()){
                        if(fornecimento.getId() == idDeletar){
                            fornecimentoDeletar = fornecimento;
                            break;
                        }
                    }
                    if(fornecimentoDeletar != null){
                        sistema.removerFornecimento(fornecimentoDeletar);
                        sistema.deletarFornecimento(fornecimentoDeletar);
                        System.out.println("Fornecimento deletado com sucesso!");
                    }
                
                } else if(opcao2 == 3){
                    
                    System.out.println("Digite o ID do fornecimento a ser atualizado:");
                    for(Fornecimento fornecimento: sistema.getFornecimentos()){
                        System.out.println(fornecimento.getId() + " - " + fornecimento.getProduto());
                    }
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    for(Fornecimento fornecimento: sistema.getFornecimentos()){
                        if(fornecimento.getId() == idAtualizar){
                            System.out.println("Selecione o novo fornecedor:");
                            for(Fornecedor fornecedor: sistema.getFornecedores()){
                                System.out.println(fornecedor.getCnpj() + " - " + fornecedor.getNome());
                            }
                            String cnpjFornecedorNovo = scanner.nextLine();
                            Fornecedor fornecedorNovo = sistema.buscarFornecedor(cnpjFornecedorNovo);
                            System.out.println("Digite o novo nome do produto:");
                            String nomeProdutoNovo = scanner.nextLine();
                            sistema.atualizarFornecimento(fornecimento, fornecedorNovo, nomeProdutoNovo);
                            System.out.println("Fornecimento atualizado com sucesso!");
                        }
                    }
                }
                    break;

                    case 8:
                    sistema.mostrarTudo(o);
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                
            
                default:
                    break;
            }
        
        }while(opcao != 0);

            scanner.close();

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o sistema: " + e.getMessage());
        }
    }
    
}