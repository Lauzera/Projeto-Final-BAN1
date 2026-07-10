package dados;

public class Mecanico{
    private String cpf;
    private String nome;
    private double salario;
    private Oficina oficina;

    public Mecanico(String cpf, String nome, double salario, Oficina oficina){
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.oficina = oficina;
    }
    
    public String getCpf(){
        return this.cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public double getSalario(){
        return this.salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }

    public Oficina getOficina(){
        return this.oficina;
    }

    public String toString(){
        String mecanico = "";
        mecanico += "Nome: " +nome+ "\n";
        mecanico += "CPF: " +cpf+ "\n";
        mecanico += "Salario: " +salario+ "\n";
        mecanico += "Oficina: " +oficina.getNome()+ "\n";

        return mecanico;
    }

}
