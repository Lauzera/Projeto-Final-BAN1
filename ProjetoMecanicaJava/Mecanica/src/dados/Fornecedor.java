package dados;

public class Fornecedor{
    private String nome;
    private String cnpj;
    private String telefone;
    

    public Fornecedor(String nome, String cnpj, String telefone){
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCnpj(){
        return this.cnpj;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String toString(){
        String fornecedor = "";
        fornecedor += "Nome: " +nome+ "\n";
        fornecedor += "CNPJ: " +cnpj+ "\n";
        fornecedor += "Telefone: " +telefone+ "\n";
        
        return fornecedor;
    }
}
