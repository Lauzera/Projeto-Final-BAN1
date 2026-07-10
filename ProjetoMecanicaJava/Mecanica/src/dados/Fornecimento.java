package dados;

public class Fornecimento{
    private int id;
    private Fornecedor fornecedor;
    private Oficina oficina;
    private String produto;

    public Fornecimento(int id, Fornecedor fornecedor, Oficina oficina, String produto){
        this.id = id;
        this.fornecedor = fornecedor;
        this.oficina = oficina;
        this.produto = produto;
    }
    public Fornecimento(Fornecedor fornecedor, Oficina oficina, String produto){
        this.fornecedor = fornecedor;
        this.oficina = oficina;
        this.produto = produto;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Fornecedor getFornecedor(){
        return this.fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor = fornecedor;
    }
    
    public Oficina getOficina(){
        return this.oficina;
    }
    public String getProduto(){
        return this.produto;
    }
    public void setProduto(String produto){
        this.produto = produto;
    }

    public String toString(){
        String fornecimento = "";
        fornecimento += "Id: " +id+ "\n";
        fornecimento += "Fornecedor: " +fornecedor.getNome()+ "\n";
        fornecimento += "Oficina: " +oficina.getNome()+ "\n";
        fornecimento += "Produto: " +produto+ "\n";
        
        return fornecimento;
    }
}
