package persistencia;
import dados.Servico;
import dados.Veiculo;
import dados.Mecanico;
import dados.Conserto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import exception.UpdateException;

public class ConsertoDAO{
    private static ConsertoDAO instance = null;

    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    private ConsertoDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into conserto(id_servico, cpf_mecanico, placa_veiculo, descricao, valor, data_inicio, data_fim) values (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        select = conexao.prepareStatement("select * from conserto where id = ?");
        update = conexao.prepareStatement("update conserto set id_servico = ?, cpf_mecanico = ? , placa_veiculo = ?, descricao = ?, valor = ?, data_inicio = ?, data_fim = ? where id = ?");
        delete = conexao.prepareStatement("delete from conserto where id = ?");
    }

    public static ConsertoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new ConsertoDAO();
        }
        return instance;
    }

    public List<Conserto> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Conserto> consertos = new ArrayList<>();

        String sql = "SELECT * FROM conserto";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Servico servico = ServicoDAO.getInstance().select(rs.getInt("id_servico"));
                Mecanico mecanico = MecanicoDAO.getInstance().select(rs.getString("cpf_mecanico"));
                Veiculo veiculo = VeiculoDAO.getInstance().select(rs.getString("placa_veiculo"));
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String dataInicio = rs.getString("data_inicio");
                String dataFim = rs.getString("data_fim");
                Conserto c = new Conserto(rs.getInt("id"), servico, mecanico, veiculo, descricao, valor, dataInicio, dataFim);

                consertos.add(c);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar consertos");
        }

        return consertos;
    }

    public void insert(Conserto conserto) throws InsertException, SelectException{
        try{
            insert.setInt(1, conserto.getServico().getId());
            insert.setString(2, conserto.getMecanico().getCpf());
            insert.setString(3, conserto.getVeiculo().getPlaca());
            insert.setString(4, conserto.getDescricao());
            insert.setDouble(5, conserto.getValor());
            insert.setString(6, conserto.getDataInicio());
            insert.setString(7, conserto.getDataFim());
            insert.executeUpdate();
            ResultSet rs = insert.getGeneratedKeys();
            if(rs.next()){
                conserto.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir conserto");
        }
    }

    public Conserto select(int id) throws SelectException, ClassNotFoundException{
        try{
            select.setInt(1, id);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                Servico servico = ServicoDAO.getInstance().select(rs.getInt("id_servico"));
                Mecanico mecanico = MecanicoDAO.getInstance().select(rs.getString("cpf_mecanico"));
                Veiculo veiculo = VeiculoDAO.getInstance().select(rs.getString("placa_veiculo"));
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String dataInicio = rs.getString("data_inicio");
                String dataFim = rs.getString("data_fim");
                return new Conserto(id, servico, mecanico, veiculo, descricao, valor, dataInicio, dataFim);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar conserto");
        }
        return null;
    }

    public void update(Conserto conserto) throws UpdateException{
        try{
            update.setInt(8, conserto.getId());
            update.setInt(1, conserto.getServico().getId());
            update.setString(2, conserto.getMecanico().getCpf());
            update.setString(3, conserto.getVeiculo().getPlaca());
            update.setString(4, conserto.getDescricao());
            update.setDouble(5, conserto.getValor());
            update.setString(6, conserto.getDataInicio());
            update.setString(7, conserto.getDataFim());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar conserto");
        }
    }

    public void delete(Conserto conserto) throws DeleteException{
        try{
            delete.setInt(1, conserto.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar conserto");
        }
    }
}

