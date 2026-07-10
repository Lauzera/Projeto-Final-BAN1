package persistencia;

import dados.Veiculo;
import dados.Cliente;
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

public class VeiculoDAO{
    private static VeiculoDAO instance = null;
    
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    private VeiculoDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into veiculo(placa, nome, cor, id_cliente) values (?, ?, ?, ?)");
        select = conexao.prepareStatement("select * from veiculo where placa = ?");
        update = conexao.prepareStatement("update veiculo set nome = ?, cor = ?, id_cliente = ? where placa = ?");
        delete = conexao.prepareStatement("delete from veiculo where placa = ?");
    }

    public static VeiculoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new VeiculoDAO();
        }
        return instance;
    }

    public List<Veiculo> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Veiculo> veiculos = new ArrayList<>();

        String sql = "SELECT * FROM veiculo";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Cliente cliente = ClienteDAO.getInstance().select(rs.getInt("id_cliente"));
                Veiculo v = new Veiculo(rs.getString("nome"), rs.getString("placa"), rs.getString("cor"), cliente);
                veiculos.add(v);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar veiculos");
        }

        return veiculos;
    }


    public void insert(Veiculo veiculo) throws InsertException, SelectException{
        try{
            insert.setString(1, veiculo.getPlaca());
            insert.setString(2, veiculo.getNome());
            insert.setString(3, veiculo.getCor());
            insert.setInt(4, veiculo.getCliente().getId());
            insert.executeUpdate();
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir veiculo");
        }
    }

    public Veiculo select(String placa) throws SelectException, ClassNotFoundException{
        try{
            select.setString(1, placa);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                Cliente cliente = ClienteDAO.getInstance().select(rs.getInt("id_cliente"));
                return new Veiculo(nome, placa, cor, cliente);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao selecionar veiculo");
        } 
        return null;
    }

    public void update(Veiculo veiculo) throws UpdateException{
        try{
            update.setString(4, veiculo.getPlaca());
            update.setString(1, veiculo.getNome());
            update.setString(2, veiculo.getCor());
            update.setInt(3, veiculo.getCliente().getId());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar veiculo");
        }
    }

    public void delete(Veiculo veiculo) throws DeleteException{
        try{
            delete.setString(1, veiculo.getPlaca());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar veiculo");
        }
    }
}
