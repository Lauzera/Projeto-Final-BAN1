package persistencia;

import dados.Oficina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import exception.UpdateException;

public class OficinaDAO{
    private static OficinaDAO instance = null;
    
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    private OficinaDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into oficina values (?, ?, ?)");
        select = conexao.prepareStatement("select * from oficina where cnpj = ?");
        update = conexao.prepareStatement("update oficina set nome = ?, email = ? where cnpj = ?");
        delete = conexao.prepareStatement("delete from oficina where cnpj = ?");
    }

    public static OficinaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new OficinaDAO();
        }
        return instance;
    }

    public void insert(Oficina oficina) throws InsertException, SelectException{
        try{
            insert.setString(1, oficina.getCnpj());
            insert.setString(2, oficina.getNome());
            insert.setString(3, oficina.getEmail());
            insert.executeUpdate();
        }catch (SQLException e) {
            throw new InsertException("Erro ao inserir oficina");
        }
    }

    public Oficina select(String cnpj) throws SelectException{
        try{
            select.setString(1, cnpj);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                return new Oficina(rs.getString("nome"), rs.getString("cnpj"), rs.getString("email"));
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar oficina");
        }
        return null;
    }

    public void update(Oficina oficina) throws UpdateException{
        try{
            update.setString(3, oficina.getCnpj());
            update.setString(1, oficina.getNome());
            update.setString(2, oficina.getEmail());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar oficina");
        }
    }

    public void delete(Oficina oficina) throws DeleteException{
        try{
            delete.setString(1, oficina.getCnpj());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar oficina");
        }
    }
}
