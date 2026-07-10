package persistencia;

import dados.Oficina;
import dados.Fornecedor;
import dados.Fornecimento;
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

public class FornecimentoDAO{
    private static FornecimentoDAO instance = null;

    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    private FornecimentoDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into fornecimento(cnpj_fornecedor, cnpj_oficina, produto) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        select = conexao.prepareStatement("select * from fornecimento where id = ?");
        update = conexao.prepareStatement("update fornecimento set cnpj_fornecedor = ?, cnpj_oficina = ? , produto = ? where id = ?");
        delete = conexao.prepareStatement("delete from fornecimento where id = ?");
    }

    public static FornecimentoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new FornecimentoDAO();
        }
        return instance;
    }

    public List<Fornecimento> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Fornecimento> fornecimentos = new ArrayList<>();

        String sql = "SELECT * FROM fornecimento";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Fornecedor fornecedor = FornecedorDAO.getInstance().select(rs.getString("cnpj_fornecedor"));
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                String produto = rs.getString("produto");
                Fornecimento f = new Fornecimento(rs.getInt("id"), fornecedor, oficina, produto);
                fornecimentos.add(f);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar fornecimentos");
        }

        return fornecimentos;
    }

    public void insert(Fornecimento fornecimento) throws InsertException, SelectException{
        try{
            insert.setString(1, fornecimento.getFornecedor().getCnpj());
            insert.setString(2, fornecimento.getOficina().getCnpj());
            insert.setString(3, fornecimento.getProduto());
            insert.executeUpdate();
            ResultSet rs = insert.getGeneratedKeys();
            if(rs.next()){
                fornecimento.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir fornecimento");
        }
    }

    public Fornecimento select(int id) throws SelectException, ClassNotFoundException{
        try{
            select.setInt(1, id);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                Fornecedor fornecedor = FornecedorDAO.getInstance().select(rs.getString("cnpj_fornecedor"));
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                String produto = rs.getString("produto");
                return new Fornecimento(id, fornecedor, oficina, produto);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar fornecimento");
        }
        return null;
    }

    public void update(Fornecimento fornecimento) throws UpdateException{
        try{
            update.setString(1, fornecimento.getFornecedor().getCnpj());
            update.setString(2, fornecimento.getOficina().getCnpj());
            update.setString(3, fornecimento.getProduto());
            update.setInt(4, fornecimento.getId());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar fornecimento");
        }
    }

    public void delete(Fornecimento fornecimento) throws DeleteException{
        try{
            delete.setInt(1, fornecimento.getId());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar fornecimento");
        }
    }

}
