package dados;

public class Servico{
    private int id;
    private Oficina oficina;
    private Cliente cliente;
    private Veiculo veiculo;

    public Servico(int id, Oficina oficina, Cliente cliente, Veiculo veiculo){
        this.id = id;
        this.oficina = oficina;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public Servico(Oficina oficina, Cliente cliente, Veiculo veiculo){
        this.oficina = oficina;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public Oficina getOficina(){
        return this.oficina;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Veiculo getVeiculo(){
        return this.veiculo;
    }
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    public String toString(){
        String servico = "";
        servico += "ID: " +id+ "\n";
        servico += "Oficina: " +oficina.getNome()+ "\n";
        servico += "Cliente: " +cliente.getNome()+ "\n";
        servico += "Veiculo: " +veiculo.getNome()+ "\n";

        return servico;
    }
}
