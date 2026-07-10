package persistencia;

import dados.Mecanico;
import dados.Oficina;
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

public class MecanicoDAO{
    private static MecanicoDAO instance = null;
    
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

     private MecanicoDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        
        insert = conexao.prepareStatement("insert into mecanico(cpf, nome, salario, cnpj_oficina) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        select = conexao.prepareStatement("select * from mecanico where cpf = ?");
        update = conexao.prepareStatement("update mecanico set nome = ?, salario = ?, cnpj_oficina = ? where cpf = ?");
        delete = conexao.prepareStatement("delete from mecanico where cpf = ?");
    }

    public static MecanicoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new MecanicoDAO();
        }
        return instance;
    }

    public List<Mecanico> selecionarTodos() throws SelectException, ClassNotFoundException{
        List<Mecanico> mecanicos = new ArrayList<>();

        String sql = "SELECT * FROM mecanico";

        try (
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                Mecanico m = new Mecanico(cpf, nome, salario, oficina);
                mecanicos.add(m);
            }
        } catch(SQLException e){
            throw new SelectException("Erro ao selecionar mecanicos");
        }

        return mecanicos;
    }

    public void insert(Mecanico mecanico) throws InsertException, SelectException{
        try{
            insert.setString(1, mecanico.getCpf());
            insert.setString(2, mecanico.getNome());
            insert.setDouble(3, mecanico.getSalario());
            insert.setString(4, mecanico.getOficina().getCnpj());
            insert.executeUpdate();
        }catch (SQLException e){
            throw new InsertException("Erro ao inserir mecanico");
        }
    }

    public Mecanico select(String cpf) throws SelectException, ClassNotFoundException{
        try{
            select.setString(1, cpf);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                Oficina oficina = OficinaDAO.getInstance().select(rs.getString("cnpj_oficina"));
                return new Mecanico(cpf, nome, salario, oficina);
            }
        }catch (SQLException e){
            throw new SelectException("Erro ao buscar mecanico");
        }
        return null;
    }

    public void update(Mecanico mecanico) throws UpdateException{
        try {
            update.setString(4, mecanico.getCpf());
            update.setString(1, mecanico.getNome());
            update.setDouble(2, mecanico.getSalario());
            update.setString(3, mecanico.getOficina().getCnpj());
            update.executeUpdate();
        }catch (SQLException e){
            throw new UpdateException("Erro ao atualizar mecanico");
        }
    }

    public void delete(Mecanico mecanico) throws DeleteException{
        try{
            delete.setString(1, mecanico.getCpf());
            delete.executeUpdate();
        }catch (SQLException e){
            throw new DeleteException("Erro ao deletar mecanico");
        }
    }


}
