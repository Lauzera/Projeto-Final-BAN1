package persistencia;

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

public class ClienteDAO{
    private static ClienteDAO instance = null;
    
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    
    public static ClienteDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new ClienteDAO();
        }
        return instance;
    }

    private ClienteDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into cliente(nome, telefone) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        select = conexao.prepareStatement("select * from cliente where id = ?");
        update = conexao.prepareStatement("update cliente set nome = ?, telefone = ? where id = ?");
        delete = conexao.prepareStatement("delete from cliente where id = ?");
    }

    public List<Cliente> selecionarTodos() throws SelectException, ClassNotFoundException{
    List<Cliente> clientes = new ArrayList<>();

    String sql = "SELECT * FROM cliente";

    try (
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()
    ){
        while(rs.next()){
            Cliente c = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"));

            clientes.add(c);
        }
    } catch(SQLException e){
        throw new SelectException("Erro ao selecionar clientes");
    }

    return clientes;
}

    public void insert(Cliente cliente) throws InsertException, SelectException{
        try{
            insert.setString(1, cliente.getNome());
            insert.setString(2, cliente.getTelefone());
            insert.executeUpdate();
            ResultSet rs = insert.getGeneratedKeys();
            if(rs.next()){
                cliente.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir cliente");
        }
    }

    public Cliente select(int id) throws SelectException, ClassNotFoundException{
        try{
            select.setInt(1, id);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                return new Cliente(id, nome, telefone);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar cliente");
        }
        return null;
    }

    public void update(Cliente cliente) throws UpdateException{
        try{
            update.setString(1, cliente.getNome());
            update.setString(2, cliente.getTelefone());
            update.setInt(3, cliente.getId());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar cliente");
        }
    }

    public void delete(Cliente cliente) throws DeleteException{
        try{
            delete.setInt(1, cliente.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar cliente");
        }
    }

}
