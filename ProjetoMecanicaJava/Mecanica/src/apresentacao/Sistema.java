package apresentacao;
import persistencia.*;
import dados.*;
import exception.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class Sistema{
    private ClienteDAO clienteDAO;
    private FornecedorDAO fornecedorDAO;
    private MecanicoDAO mecanicoDAO;
    private OficinaDAO oficinaDAO;
    private VeiculoDAO veiculoDAO;
    private ServicoDAO servicoDAO;
    private ConsertoDAO consertoDAO;
    private FornecimentoDAO fornecimentoDAO;

    private List<Fornecedor> fornecedores;
    private List<Cliente> clientes;
    private List<Mecanico> mecanicos;
    private List<Veiculo> veiculos;
    private List<Servico> servicos;
    private List<Conserto> consertos;
    private List<Fornecimento> fornecimentos;
    
    public Sistema(String senha) throws ClassNotFoundException, SQLException, SelectException{
        
        clienteDAO = ClienteDAO.getInstance();
        fornecedorDAO = FornecedorDAO.getInstance();
        mecanicoDAO = MecanicoDAO.getInstance();
        oficinaDAO = OficinaDAO.getInstance();
        veiculoDAO = VeiculoDAO.getInstance();
        servicoDAO = ServicoDAO.getInstance();
        consertoDAO = ConsertoDAO.getInstance();
        fornecimentoDAO = FornecimentoDAO.getInstance();

        this.clientes = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
        this.mecanicos = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.servicos = new ArrayList<>();
        this.consertos = new ArrayList<>();
        this.fornecimentos = new ArrayList<>();
    }

    public void carregarDados() throws SelectException, ClassNotFoundException{
        this.clientes = clienteDAO.selecionarTodos();
        this.fornecedores = fornecedorDAO.selecionarTodos();
        this.mecanicos = mecanicoDAO.selecionarTodos();
        this.veiculos = veiculoDAO.selecionarTodos();
        this.servicos = servicoDAO.selecionarTodos();
        this.consertos = consertoDAO.selecionarTodos();
        this.fornecimentos = fornecimentoDAO.selecionarTodos();
    }

    public void menu(){
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Gerenciar clientes");
        System.out.println("2 - Gerenciar fornecedores");
        System.out.println("3 - Gerenciar mecânicos");
        System.out.println("4 - Gerenciar veículos");
        System.out.println("5 - Gerenciar servicos");
        System.out.println("6 - Gerenciar consertos");
        System.out.println("7 - Gerenciar fornecimentos");
        System.out.println("8 - Mostrar tudo");
        System.out.println("0 - Sair");
    }

    public void opcoes(){
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Inserir");
        System.out.println("2 - Deletar");
        System.out.println("3 - Atualizar");
        System.out.println("0 - Voltar");
    }

    public void mostrarTudo(Oficina o){
        System.out.println("Oficina:");
        System.out.println(o.toString());
        System.out.println("\nClientes:");
        for(Cliente cliente: clientes){
            System.out.println(cliente.toString());
        }
        System.out.println("\nMecanicos:");
        for(Mecanico mecanico: mecanicos){
            System.out.println(mecanico.toString());
        }
        System.out.println("\nVeiculos:");
        for(Veiculo veiculo: veiculos){
            System.out.println(veiculo.toString());
        }
        System.out.println("\nFornecedores:");
        for(Fornecedor fornecedor: fornecedores){
            System.out.println(fornecedor.toString());
        }
        System.out.println("\nServicos:");
        for(Servico servico: servicos){
            System.out.println(servico.toString());
        }
        System.out.println("\nConsertos:");
        for(Conserto conserto: consertos){
            System.out.println(conserto.toString());
        }
        System.out.println("\nFornecimentos:");
        for(Fornecimento fornecimento: fornecimentos){
            System.out.println(fornecimento.toString());
        }
    }

    public List<Fornecedor> getFornecedores(){
        return this.fornecedores;
    }
    public List<Cliente> getClientes(){
        return this.clientes;
    }
    public List<Mecanico> getMecanicos(){
        return this.mecanicos;
    }
    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }
    public List<Servico> getServicos(){
        return this.servicos;
    }
    public List<Conserto> getConsertos(){
        return this.consertos;
    }
    public List<Fornecimento> getFornecimentos(){
        return this.fornecimentos;
    }

    public void adicionarFornecedor(Fornecedor fornecedor){
        fornecedores.add(fornecedor);
    }
    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public void adicionarMecanico(Mecanico mecanico){
        mecanicos.add(mecanico);
    }
    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }
    public void adicionarServico(Servico servico){
        servicos.add(servico);
    }
    public void adicionarConserto(Conserto conserto){
        consertos.add(conserto);
    }
    public void adicionarFornecimento(Fornecimento fornecimento){
        fornecimentos.add(fornecimento);
    }

    public void removerFornecedor(Fornecedor fornecedor){
        fornecedores.remove(fornecedor);
    }
    public void removerCliente(Cliente cliente){
        clientes.remove(cliente);
    }
    public void removerMecanico(Mecanico mecanico){
        mecanicos.remove(mecanico);
    }
    public void removerVeiculo(Veiculo veiculo){
        veiculos.remove(veiculo);
    }
    public void removerServico(Servico servico){
        servicos.remove(servico);
    }
    public void removerConserto(Conserto conserto){
        consertos.remove(conserto);
    }
    public void removerFornecimento(Fornecimento fornecimento){
        fornecimentos.remove(fornecimento);
    }
    

    public Fornecedor buscarFornecedor(String cnpj){
        for(Fornecedor fornecedor: fornecedores){
            if(fornecedor.getCnpj().equals(cnpj)){
                return fornecedor;
            }
        }
        return null;
    }
    public Cliente buscarCliente(int id){
        for(Cliente cliente: clientes){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }
    public Mecanico buscarMecanico(String cpf){
        for(Mecanico mecanico: mecanicos){
            if(mecanico.getCpf().equals(cpf)){
                return mecanico;
            }
        }
        return null;
    }
    public Veiculo buscarVeiculo(String placa){
        for(Veiculo veiculo: veiculos){
            if(veiculo.getPlaca().equals(placa)){
                return veiculo;
            }
        }
        return null;
    }
    public Servico buscarServico(int id){
        for(Servico servico: servicos){
            if(servico.getId() == id){
                return servico;
            }
        }
        return null;
    }
    public Conserto buscarConserto(int id){
        for(Conserto conserto: consertos){
            if(conserto.getId() == id){
                return conserto;
            }
        }
        return null;
    }
    

    ////////
    
    public void inserirCliente(Cliente c) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        clienteDAO.insert(c);
    }
    public void deletarCliente(Cliente c) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        clienteDAO.delete(c);
    }
    public void atualizarCliente(Cliente c, String nome, String telefone) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        c.setNome(nome);
        c.setTelefone(telefone);
        clienteDAO.update(c);
    }
    ////////
    public void inserirFornecedor(Fornecedor f) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        fornecedorDAO.insert(f);
    }
    public void deletarFornecedor(Fornecedor f) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        fornecedorDAO.delete(f);
    }
    public void atualizarFornecedor(Fornecedor f, String nome, String telefone) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        f.setNome(nome);
        f.setTelefone(telefone);
        fornecedorDAO.update(f);
    }
    ////////
    public void inserirMecanico(Mecanico m) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        mecanicoDAO.insert(m);
    }
    public void deletarMecanico(Mecanico m) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        mecanicoDAO.delete(m);
    }
    public void atualizarMecanico(Mecanico m, String nome, double salario) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        m.setNome(nome);
        m.setSalario(salario);
        mecanicoDAO.update(m);
    }
    ////////
    public void inserirOficina(Oficina o) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        oficinaDAO.insert(o);
    }
    public void deletarOficina(Oficina o) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        oficinaDAO.delete(o);
    }
    public void atualizarOficina(Oficina o) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        oficinaDAO.update(o);
    }
    ////////
    public void inserirVeiculo(Veiculo v) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        veiculoDAO.insert(v);
    }
    public void deletarVeiculo(Veiculo v) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        veiculoDAO.delete(v);
    }
    public void atualizarVeiculo(Veiculo v, String nome, String cor) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        v.setNome(nome);
        v.setCor(cor);
        veiculoDAO.update(v);
    }
    ////////
    public void inserirServico(Servico s) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        servicoDAO.insert(s);
    }
    public void deletarServico(Servico s) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        servicoDAO.delete(s);
    }
    public void atualizarServico(Servico s, Cliente cliente, Veiculo veiculo) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        s.setCliente(cliente);
        s.setVeiculo(veiculo);
        servicoDAO.update(s);
    }
    ////////
    public void inserirConserto(Conserto c) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        consertoDAO.insert(c);
    }
    public void deletarConserto(Conserto c) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        consertoDAO.delete(c);
    }
    public void atualizarConserto(Conserto c, String descricao, double valor, String dataInicio, String dataFim) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        c.setDescricao(descricao);
        c.setValor(valor);
        c.setDataInicio(dataInicio);
        c.setDataFim(dataFim);
        consertoDAO.update(c);
    }
    ////////
    public void inserirFornecimento(Fornecimento f) throws InsertException, SelectException, ClassNotFoundException, SQLException{
        fornecimentoDAO.insert(f);
    }
    public void deletarFornecimento(Fornecimento f) throws DeleteException, SelectException, ClassNotFoundException, SQLException{
        fornecimentoDAO.delete(f);
    }
    public void atualizarFornecimento(Fornecimento f, Fornecedor fornecedor, String produto) throws UpdateException, SelectException, ClassNotFoundException, SQLException{
        f.setFornecedor(fornecedor);
        f.setProduto(produto);
        fornecimentoDAO.update(f);
    }
}