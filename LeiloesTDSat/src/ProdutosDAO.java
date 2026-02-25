import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    // =========================
    // CADASTRAR PRODUTO
    // =========================
    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = new conectaDAO().conectaBD();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            pstm.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try { if (pstm != null) pstm.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // =========================
    // LISTAR TODOS PRODUTOS
    // =========================
    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = new conectaDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstm != null) pstm.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return lista;
    }

    // =========================
    // VENDER PRODUTO
    // =========================
    public void venderProduto(int idProduto) {

        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = new conectaDAO().conectaBD();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, "Vendido");
            pstm.setInt(2, idProduto);

            pstm.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            try { if (pstm != null) pstm.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // =========================
    // LISTAR PRODUTOS VENDIDOS
    // =========================
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        String sql = "SELECT * FROM produtos WHERE status = ?";

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = new conectaDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "Vendido");

            rs = pstm.executeQuery();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstm != null) pstm.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return lista;
    }
}