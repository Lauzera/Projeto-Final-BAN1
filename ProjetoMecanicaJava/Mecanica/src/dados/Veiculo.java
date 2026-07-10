package dados;

public class Veiculo{
    private String nome;
    private String placa;
    private String cor;
    private Cliente cliente;

    public Veiculo(String nome, String placa, String cor, Cliente cliente){
        this.nome = nome;
        this.placa = placa;
        this.cor = cor;
        this.cliente = cliente;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getPlaca(){
        return this.placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }


    public String getCor(){
        return this.cor;
    }
    public void setCor(String cor){
        this.cor = cor;
    }

    public Cliente getCliente(){
        return this.cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public String toString(){
        String veiculo = "";
        veiculo += "Nome: " +nome+ "\n";
        veiculo += "Placa: " +placa+ "\n";
        veiculo += "Cor: " +cor+ "\n";
        veiculo += "Cliente: " +cliente.getId()+ "\n";
        
        return veiculo;
    }

}
