package dados;

public class Conserto{
    private int id;
    private Servico servico;
    private Mecanico mecanico;  
    private Veiculo veiculo;
    private String descricao;
    private double valor;
    private String data_inicio;
    private String data_fim;

    public Conserto(int id, Servico servico, Mecanico mecanico, Veiculo veiculo, String descricao, double valor, String data_inicio, String data_fim){
        this.id = id;
        this.servico = servico;
        this.mecanico = mecanico;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Conserto(Servico servico, Mecanico mecanico, Veiculo veiculo, String descricao, double valor, String data_inicio, String data_fim){
        this.servico = servico;
        this.mecanico = mecanico;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Servico getServico(){
        return this.servico;
    }
    public Mecanico getMecanico(){
        return this.mecanico;
    }
    public void setMecanico(Mecanico mecanico){
        this.mecanico = mecanico;
    }
    
    public Veiculo getVeiculo(){
        return this.veiculo;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public double getValor(){
        return this.valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public String getDataInicio(){
        return this.data_inicio;
    }
    public void setDataInicio(String data_inicio){
        this.data_inicio = data_inicio;
    }
    
    public String getDataFim(){
        return this.data_fim;
    }
    public void setDataFim(String data_fim){
        this.data_fim = data_fim;
    }

    public String toString(){
        String conserto = "";
        conserto += "Id: " +id+ "\n";
        conserto += "Servico: " +servico.getId()+ "\n";
        conserto += "Mecanico: " +mecanico.getNome()+ "\n";
        conserto += "Veiculo: " +veiculo.getNome()+ "\n";
        conserto += "Descricao: " +descricao+ "\n";
        conserto += "Valor: " +valor+ "\n";
        conserto += "Data Inicio: " +data_inicio+ "\n";
        conserto += "Data Fim: " +data_fim+ "\n";

        return conserto;
    }
}
