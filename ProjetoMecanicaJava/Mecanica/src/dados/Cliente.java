package dados;
import java.util.List;
import java.util.ArrayList;

public class Cliente{
    private int id;
    private String nome;
    private String telefone;
    private List<Veiculo> veiculos;

    public Cliente(int id, String nome, String telefone){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.veiculos = new ArrayList<>();
    }

    public Cliente(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
        this.veiculos = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

    public String toString(){
        String cliente = "";
        cliente += "Id: " +id+ "\n";
        cliente += "Nome: " +nome+ "\n";
        cliente += "Telefone: " +telefone+ "\n";
        
        return cliente;
    }
}
