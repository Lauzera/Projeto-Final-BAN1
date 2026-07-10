package dados;

public class Oficina{
    private String nome;
    private String cnpj;
    private String email;

    public Oficina(String nome, String cnpj, String email){
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
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

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public String toString(){
        String oficina = "";
        oficina += "" +nome+ "\n";
        oficina += "CNPJ: " +cnpj+ "\n";
        oficina += "Email: " +email;
        
        return oficina;
    }

}
