package autosell.vistas.estatisticas;

import autosell.enumeracoes.TipoColaborador;
import autosell.enumeracoes.TipoTransacao;
import autosell.gestores.*;
import autosell.modelos.Estabelecimento;
import autosell.modelos.Intervencao;
import autosell.utils.TableModel;

public class JanelaEstatisticas extends javax.swing.JInternalFrame {
    
    public JanelaEstatisticas() {
        initComponents();
        
        String[] columns = new String[]{""};
        TableModel tableModel = new TableModel(columns, getTableData());
        tableEstatisticas.setModel(tableModel);
    }
    
    private Object[][] getTableData() {
        var aux = new Object[16][1];
        
        aux[0][0] = new StringBuilder("Número total de veículos:")
                .append(GestorVeiculos.getInstance().getListagem().size())
                .toString();
        
        aux[1][0] = new StringBuilder("Número total de Interveções:")
                .append(GestorIntervencoes.getInstance().getListagem().size())
                .toString();
        
        int countConsumiveis = 0;
        for (Intervencao in : GestorIntervencoes.getInstance().getListagem()) {
            countConsumiveis += in.getConsumiveis().size();
        }
        
        aux[2][0] = new StringBuilder("Número total de consumíeis gastos: ")
                .append(countConsumiveis)
                .toString();
        
        aux[3][0] = new StringBuilder("Número total de artigos:")
                .append(GestorArtigos.getInstance().getListagem().size())
                .toString();
        
        aux[4][0] = new StringBuilder("Número total de entidades:")
                .append(GestorEntidades.getInstance().getListagem().size())
                .toString();
        
        aux[5][0] = new StringBuilder("Número total de eventos:")
                .append(GestorEventos.getInstance().getListagem().size())
                .toString();
        
        aux[6][0] = new StringBuilder("Número total de Comerciais:")
                .append(GestorColaboradores.getInstance().getListagem(p
                        -> p.getTipoColaborador().equals(TipoColaborador.COMERCIAL)).size())
                .toString();
        
        aux[7][0] = new StringBuilder("Número total de Técnicos:")
                .append(GestorColaboradores.getInstance().getListagem(p
                        -> p.getTipoColaborador().equals(TipoColaborador.TECNICO)).size())
                .toString();
        
        aux[8][0] = new StringBuilder("Número total de Administradores:")
                .append(GestorColaboradores.getInstance().getListagem(p
                        -> p.getTipoColaborador().equals(TipoColaborador.ADMINISTRADOR)).size())
                .toString();
        
        Estabelecimento estabelecimentoMaisIntervencoes = null;
        int countEstabelecimentoMaisIntervencoes = 0;
        
        Estabelecimento estabelecimentoMaisVendas = null;
        int countEstabelecimentoMaisVendas = 0;
        
        Estabelecimento estabelecimentoMaisCompras = null;
        int countEstabelecimentoMaisCompras = 0;
        
        for (Estabelecimento est : GestorEstabelecimentos.getInstance().getListagem()) {
            int count = GestorIntervencoes.getInstance().getListagem(p -> p.getEstabelecimento().equals(est)).size();
            if (count > countEstabelecimentoMaisIntervencoes) {
                estabelecimentoMaisIntervencoes = est;
                countEstabelecimentoMaisIntervencoes = count;
            }
            
            int countVendas = GestorTransacoes.getInstance()
                    .getListagem(p -> p.getTipo().equals(TipoTransacao.VENDA)
                    && p.getColaborador().getEstabelecimento().equals(est)).size();
            if (countVendas > countEstabelecimentoMaisVendas) {
                countEstabelecimentoMaisVendas = countVendas;
                estabelecimentoMaisVendas = est;
            }
            
            int countCompras = GestorTransacoes.getInstance()
                    .getListagem(p -> p.getTipo().equals(TipoTransacao.COMPRA)
                    && p.getColaborador().getEstabelecimento().equals(est)).size();
            
            if (countCompras > countEstabelecimentoMaisCompras) {
                countEstabelecimentoMaisCompras = countCompras;
                estabelecimentoMaisCompras = est;
            }
        }
        
        aux[9][0] = new StringBuilder("Estabelecimento com mais intervenções: ")
                .append(estabelecimentoMaisIntervencoes != null ? estabelecimentoMaisIntervencoes.getNome() : "--")
                .toString();
        
        aux[10][0] = new StringBuilder("Estabelecimento com mais vendas: ")
                .append(estabelecimentoMaisVendas != null ? estabelecimentoMaisVendas.getNome() : "--")
                .toString();
        
        aux[11][0] = new StringBuilder("Estabelecimento com mais compras: ")
                .append(estabelecimentoMaisCompras != null ? estabelecimentoMaisCompras.getNome() : "--")
                .toString();
        
        aux[12][0] = new StringBuilder("Número Total de compras: ")
                .append(GestorTransacoes.getInstance().getListagem(
                        p -> p.getTipo().equals(TipoTransacao.COMPRA)).size())
                .toString();
        
        aux[13][0] = new StringBuilder("Número Total de vendas: ")
                .append(GestorTransacoes.getInstance().getListagem(
                        p -> p.getTipo().equals(TipoTransacao.VENDA)).size())
                .toString();
        
        aux[14][0] = new StringBuilder("Valor total de vendas: ")
                .append(GestorTransacoes.getInstance().getListagem()
                .stream()
                .filter(p -> p.getTipo().equals(TipoTransacao.VENDA))
                .mapToDouble(p -> p.getPrecoFinal()).sum())
                .toString();
                
       aux[15][0] = new StringBuilder("Valor total de compras: ")
                .append(GestorTransacoes.getInstance().getListagem()
                .stream()
                .filter(p -> p.getTipo().equals(TipoTransacao.COMPRA))
                .mapToDouble(p -> p.getPrecoFinal()).sum())
                .toString();
        
        return aux;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneEstatisticas = new javax.swing.JScrollPane();
        tableEstatisticas = new javax.swing.JTable();

        setClosable(true);

        tableEstatisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPaneEstatisticas.setViewportView(tableEstatisticas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneEstatisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneEstatisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPaneEstatisticas;
    private javax.swing.JTable tableEstatisticas;
    // End of variables declaration//GEN-END:variables
}
