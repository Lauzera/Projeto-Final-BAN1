package persistencia;

import dados.Fornecedor;
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

public class FornecedorDAO{
    private static FornecedorDAO instance = null;
    
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

        private FornecedorDAO() throws ClassNotFoundException, SQLException, SelectException{
            Connection conexao = Conexao.getConexao();
            insert = conexao.prepareStatement("insert into fornecedor(cnpj, nome, telefone) values (?, ?, ?)");
            select = conexao.prepareStatement("select * from fornecedor where cnpj = ?");
            update = conexao.prepareStatement("update fornecedor set nome = ?, telefone = ? where cnpj = ?");
            delete = conexao.prepareStatement("delete from fornecedor where cnpj = ?");
        }

    public static FornecedorDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new FornecedorDAO();
        }
        return instance;
    }

    public List<Fornecedor> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Fornecedor> fornecedores = new ArrayList<>();

        String sql = "SELECT * FROM fornecedor";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Fornecedor f = new Fornecedor(rs.getString("nome"), rs.getString("cnpj"), rs.getString("telefone"));
                fornecedores.add(f);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar fornecedores"+ e.getMessage());
        }

        return fornecedores;
    }

    public void insert(Fornecedor fornecedor) throws InsertException, SelectException{
        try{
            insert.setString(1, fornecedor.getCnpj());
            insert.setString(2, fornecedor.getNome());
            insert.setString(3, fornecedor.getTelefone());
            insert.executeUpdate();
        }catch (SQLException e) {
            throw new InsertException("Erro ao inserir fornecedor");
        }
    }

    public Fornecedor select(String cnpj) throws SelectException{
        try{
            select.setString(1, cnpj);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                return new Fornecedor(cnpj, nome, telefone);
            }
        }catch (SQLException e) {
            throw new SelectException("Erro ao buscar fornecedor");
        }
        return null;
    }

    public void update(Fornecedor fornecedor) throws UpdateException{
        try{
            update.setString(1, fornecedor.getNome());
            update.setString(3, fornecedor.getCnpj());
            update.setString(2, fornecedor.getTelefone());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar fornecedor");
        }
    }

    public void delete(Fornecedor fornecedor) throws DeleteException{
        try{
            delete.setString(1, fornecedor.getCnpj());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar fornecedor");
        }
    }
}
