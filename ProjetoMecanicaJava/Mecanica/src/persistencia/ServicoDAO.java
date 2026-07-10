package persistencia;
import dados.Servico;
import dados.Oficina;
import dados.Cliente;
import dados.Veiculo;

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

public class ServicoDAO{
    private static ServicoDAO instance = null;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    private ServicoDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into servico(cnpj_oficina, id_cliente, placa_veiculo) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        select = conexao.prepareStatement("select * from servico where id = ?");
        update = conexao.prepareStatement("update servico set cnpj_oficina = ?, id_cliente = ? , placa_veiculo = ? where id = ?");
        delete = conexao.prepareStatement("delete from servico where id = ?");
    }

    public static ServicoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new ServicoDAO();
        }
        return instance;
    }

    public List<Servico> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Servico> servicos = new ArrayList<>();

        String sql = "SELECT * FROM servico";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                Cliente cliente = ClienteDAO.getInstance().select(rs.getInt("id_cliente"));
                Veiculo veiculo = VeiculoDAO.getInstance().select(rs.getString("placa_veiculo"));
                Servico s = new Servico(rs.getInt("id"), oficina, cliente, veiculo);
                servicos.add(s);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar servicos");
        }
        return servicos;
    }

    public void insert(Servico servico) throws InsertException, SelectException{
        try{
            insert.setString(1, servico.getOficina().getCnpj());
            insert.setInt(2, servico.getCliente().getId());
            insert.setString(3, servico.getVeiculo().getPlaca());
            insert.executeUpdate();
            ResultSet rs = insert.getGeneratedKeys();
            if(rs.next()){
                servico.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir servico");
        }
    }

    public Servico select(int id) throws SelectException, ClassNotFoundException{
        try{
            select.setInt(1, id);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                Cliente cliente = ClienteDAO.getInstance().select(rs.getInt("id_cliente"));
                Veiculo veiculo = VeiculoDAO.getInstance().select(rs.getString("placa_veiculo"));
                return new Servico(id, oficina, cliente, veiculo);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar servico");
        }
        return null;
    }

    public void update(Servico servico) throws UpdateException{
        try{
            update.setInt(4, servico.getId());
            update.setString(1, servico.getOficina().getCnpj());
            update.setInt(2, servico.getCliente().getId());
            update.setString(3, servico.getVeiculo().getPlaca());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar servico");
        }
    }

    public void delete(Servico servico) throws DeleteException{
        try{
            delete.setInt(1, servico.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar servico");
        }
    }

}
