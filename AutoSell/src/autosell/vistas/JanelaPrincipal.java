package autosell.vistas;

import autosell.enumeracoes.TipoColaborador;
import autosell.enumeracoes.TipoTransacao;
import autosell.modelos.Colaborador;
import autosell.vistas.artigos.JanelaEditarArtigo;
import autosell.vistas.artigos.JanelaListagemArtigos;
import autosell.vistas.colaboradores.JanelaEditarColaborador;
import autosell.vistas.colaboradores.JanelaListagemColaboradores;
import autosell.vistas.entidades.JanelaEditarEntidade;
import autosell.vistas.entidades.JanelaListagemEntidades;
import autosell.vistas.estabelecimentos.JanelaEditarEstabelecimento;
import autosell.vistas.estabelecimentos.JanelaListagemEstabelecimentos;
import autosell.vistas.estatisticas.JanelaEstatisticas;
import autosell.vistas.eventos.JanelaEditarEvento;
import autosell.vistas.eventos.JanelaListagemEventos;
import autosell.vistas.intervencoes.JanelaEditarIntervencao;
import autosell.vistas.intervencoes.JanelaListagemIntervencoes;
import autosell.vistas.transacoes.JanelaEditarTransacao;
import autosell.vistas.transacoes.JanelaListagemTransacoes;
import autosell.vistas.veiculos.JanelaEditarVeiculo;
import autosell.vistas.veiculos.JanelaListagemVeiculos;

public class JanelaPrincipal extends javax.swing.JFrame {

    private final Colaborador colaboradorAutenticado;

    public JanelaPrincipal(Colaborador colaborador) {
        this.colaboradorAutenticado = colaborador;

        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        bloquearAcessos();

        buttonNomeDoUtilizador.setText(colaborador.getNome());
    }

    // <editor-fold defaultstate="collapsed" desc="Getter Section">
    public Colaborador getColaborador() {
        return colaboradorAutenticado;
    }
    // </editor-fold>  

    private void bloquearAcessos() {
        if (colaboradorAutenticado.getTipoColaborador().equals(TipoColaborador.COMERCIAL)) {
            menuEstatisticas.setVisible(false);
        }

        if (colaboradorAutenticado.getTipoColaborador().equals(TipoColaborador.TECNICO)) {
            menuEventos.setVisible(false);
            menuTransacoes.setVisible(false);
            menuEstabelecimento.setVisible(false);
            menuEntidades.setVisible(false);
            menuColaboradores.setVisible(false);
            menuEstatisticas.setVisible(false);
            menuItemAdicionarVeiculo.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        toolBarPrincipal = new javax.swing.JToolBar();
        buttonNomeDoUtilizador = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuVeiculos = new javax.swing.JMenu();
        menuItemAdicionarVeiculo = new javax.swing.JMenuItem();
        menuItemListagemVeiculos = new javax.swing.JMenuItem();
        menuEventos = new javax.swing.JMenu();
        menuItemAdicionarEventos = new javax.swing.JMenuItem();
        menuItemListagemEventos = new javax.swing.JMenuItem();
        menuTransacoes = new javax.swing.JMenu();
        menuItemNovaVenda = new javax.swing.JMenuItem();
        menuItemNovaCompra = new javax.swing.JMenuItem();
        menuItemListagemTransacoes = new javax.swing.JMenuItem();
        menuArmazem = new javax.swing.JMenu();
        menuItemAdicionarArtigo = new javax.swing.JMenuItem();
        menuItemListagemArtigos = new javax.swing.JMenuItem();
        menuIntervencoes = new javax.swing.JMenu();
        menuItemAdicionarIntervencao = new javax.swing.JMenuItem();
        menuItemListagemIntervencoes = new javax.swing.JMenuItem();
        menuEstabelecimento = new javax.swing.JMenu();
        menuItemAdicionarEstabelecimento = new javax.swing.JMenuItem();
        menuItemListagemEstabelecimentos = new javax.swing.JMenuItem();
        menuEntidades = new javax.swing.JMenu();
        menuItemAdicionarEntidade = new javax.swing.JMenuItem();
        menuItemListagemEntidades = new javax.swing.JMenuItem();
        menuColaboradores = new javax.swing.JMenu();
        menuItemAdicionarColaborador = new javax.swing.JMenuItem();
        menuItemListagemColaboradores = new javax.swing.JMenuItem();
        menuEstatisticas = new javax.swing.JMenu();
        menuItemVisualizarEstatisticas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AutoSell");
        setFocusCycleRoot(false);

        toolBarPrincipal.setRollover(true);

        buttonNomeDoUtilizador.setText("Nome do Utilizador");
        buttonNomeDoUtilizador.setFocusable(false);
        buttonNomeDoUtilizador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNomeDoUtilizador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNomeDoUtilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNomeDoUtilizadorActionPerformed(evt);
            }
        });
        toolBarPrincipal.add(buttonNomeDoUtilizador);

        menuVeiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/car.png"))); // NOI18N
        menuVeiculos.setText("Ve??culos");

        menuItemAdicionarVeiculo.setText("Adicionar Ve??culo");
        menuItemAdicionarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarVeiculoActionPerformed(evt);
            }
        });
        menuVeiculos.add(menuItemAdicionarVeiculo);

        menuItemListagemVeiculos.setText("Listagem de Ve??culos");
        menuItemListagemVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemVeiculosActionPerformed(evt);
            }
        });
        menuVeiculos.add(menuItemListagemVeiculos);

        menuBar.add(menuVeiculos);

        menuEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/calendar-star.png"))); // NOI18N
        menuEventos.setText("Eventos");

        menuItemAdicionarEventos.setText("Adicionar Eventos");
        menuItemAdicionarEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarEventosActionPerformed(evt);
            }
        });
        menuEventos.add(menuItemAdicionarEventos);

        menuItemListagemEventos.setText("Listagem de Eventos");
        menuItemListagemEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemEventosActionPerformed(evt);
            }
        });
        menuEventos.add(menuItemListagemEventos);

        menuBar.add(menuEventos);

        menuTransacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/swap-horizontal.png"))); // NOI18N
        menuTransacoes.setText("Transa????es");

        menuItemNovaVenda.setText("Nova Venda");
        menuItemNovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNovaVendaActionPerformed(evt);
            }
        });
        menuTransacoes.add(menuItemNovaVenda);

        menuItemNovaCompra.setText("Nova Compra");
        menuItemNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNovaCompraActionPerformed(evt);
            }
        });
        menuTransacoes.add(menuItemNovaCompra);

        menuItemListagemTransacoes.setText("Listagem de Transa????es");
        menuItemListagemTransacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemTransacoesActionPerformed(evt);
            }
        });
        menuTransacoes.add(menuItemListagemTransacoes);

        menuBar.add(menuTransacoes);

        menuArmazem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/warehouse.png"))); // NOI18N
        menuArmazem.setText("Armaz??m");

        menuItemAdicionarArtigo.setText("Adicionar Artigo");
        menuItemAdicionarArtigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarArtigoActionPerformed(evt);
            }
        });
        menuArmazem.add(menuItemAdicionarArtigo);

        menuItemListagemArtigos.setText("Listagem de Artigos");
        menuItemListagemArtigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemArtigosActionPerformed(evt);
            }
        });
        menuArmazem.add(menuItemListagemArtigos);

        menuBar.add(menuArmazem);

        menuIntervencoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/hammer-wrench.png"))); // NOI18N
        menuIntervencoes.setText("Interven????es");

        menuItemAdicionarIntervencao.setText("Adicionar Interven????o");
        menuItemAdicionarIntervencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarIntervencaoActionPerformed(evt);
            }
        });
        menuIntervencoes.add(menuItemAdicionarIntervencao);

        menuItemListagemIntervencoes.setText("Listagem de Interven????es");
        menuItemListagemIntervencoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemIntervencoesActionPerformed(evt);
            }
        });
        menuIntervencoes.add(menuItemListagemIntervencoes);

        menuBar.add(menuIntervencoes);

        menuEstabelecimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/office-building-marker.png"))); // NOI18N
        menuEstabelecimento.setText("Estabelecimentos");

        menuItemAdicionarEstabelecimento.setText("Adicionar Estabelecimento");
        menuItemAdicionarEstabelecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarEstabelecimentoActionPerformed(evt);
            }
        });
        menuEstabelecimento.add(menuItemAdicionarEstabelecimento);

        menuItemListagemEstabelecimentos.setText("Listagem de Estabelecimentos");
        menuItemListagemEstabelecimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemEstabelecimentosActionPerformed(evt);
            }
        });
        menuEstabelecimento.add(menuItemListagemEstabelecimentos);

        menuBar.add(menuEstabelecimento);

        menuEntidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/account-group.png"))); // NOI18N
        menuEntidades.setText("Entidades");

        menuItemAdicionarEntidade.setText("Adicionar Entidade");
        menuItemAdicionarEntidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarEntidadeActionPerformed(evt);
            }
        });
        menuEntidades.add(menuItemAdicionarEntidade);

        menuItemListagemEntidades.setText("Listagem de Entidades");
        menuItemListagemEntidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemEntidadesActionPerformed(evt);
            }
        });
        menuEntidades.add(menuItemListagemEntidades);

        menuBar.add(menuEntidades);

        menuColaboradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/badge-account.png"))); // NOI18N
        menuColaboradores.setText("Colaboradores");

        menuItemAdicionarColaborador.setText("Adicionar Colaborador");
        menuItemAdicionarColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAdicionarColaboradorActionPerformed(evt);
            }
        });
        menuColaboradores.add(menuItemAdicionarColaborador);

        menuItemListagemColaboradores.setText("Listagem de Colaboradores");
        menuItemListagemColaboradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListagemColaboradoresActionPerformed(evt);
            }
        });
        menuColaboradores.add(menuItemListagemColaboradores);

        menuBar.add(menuColaboradores);

        menuEstatisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autosell/Resources/chart-line.png"))); // NOI18N
        menuEstatisticas.setText("Estat??sticas");
        menuEstatisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEstatisticasActionPerformed(evt);
            }
        });

        menuItemVisualizarEstatisticas.setText("Visualizar");
        menuItemVisualizarEstatisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVisualizarEstatisticasActionPerformed(evt);
            }
        });
        menuEstatisticas.add(menuItemVisualizarEstatisticas);

        menuBar.add(menuEstatisticas);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desktopPane)
                    .addComponent(toolBarPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(toolBarPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAdicionarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarVeiculoActionPerformed
        var janela = new JanelaEditarVeiculo(null);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarVeiculoActionPerformed

    private void menuItemListagemVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemVeiculosActionPerformed
        var janelaListagem = new JanelaListagemVeiculos(desktopPane, colaboradorAutenticado);
        desktopPane.add(janelaListagem);
        janelaListagem.setVisible(true);
    }//GEN-LAST:event_menuItemListagemVeiculosActionPerformed

    private void menuItemAdicionarEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarEventosActionPerformed
        var janela = new JanelaEditarEvento(null);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarEventosActionPerformed

    private void menuItemListagemEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemEventosActionPerformed
        var janelaListagem = new JanelaListagemEventos(desktopPane, colaboradorAutenticado);
        desktopPane.add(janelaListagem);
        janelaListagem.setVisible(true);
    }//GEN-LAST:event_menuItemListagemEventosActionPerformed

    private void menuItemNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNovaCompraActionPerformed
        var janela = new JanelaEditarTransacao(null, colaboradorAutenticado, TipoTransacao.COMPRA);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemNovaCompraActionPerformed

    private void menuItemListagemTransacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemTransacoesActionPerformed
        var janela = new JanelaListagemTransacoes(desktopPane, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemListagemTransacoesActionPerformed

    private void menuItemAdicionarArtigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarArtigoActionPerformed
        var janela = new JanelaEditarArtigo(null, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarArtigoActionPerformed

    private void menuItemListagemArtigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemArtigosActionPerformed
        var janela = new JanelaListagemArtigos(desktopPane, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemListagemArtigosActionPerformed

    private void menuItemAdicionarIntervencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarIntervencaoActionPerformed
        var janela = new JanelaEditarIntervencao(null, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarIntervencaoActionPerformed

    private void menuItemListagemIntervencoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemIntervencoesActionPerformed
        var janela = new JanelaListagemIntervencoes(desktopPane, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);

    }//GEN-LAST:event_menuItemListagemIntervencoesActionPerformed

    private void menuItemAdicionarEstabelecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarEstabelecimentoActionPerformed
        var janela = new JanelaEditarEstabelecimento(null);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarEstabelecimentoActionPerformed

    private void menuItemListagemEstabelecimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemEstabelecimentosActionPerformed
        var janelaListagem = new JanelaListagemEstabelecimentos(desktopPane, colaboradorAutenticado);
        desktopPane.add(janelaListagem);
        janelaListagem.setVisible(true);
    }//GEN-LAST:event_menuItemListagemEstabelecimentosActionPerformed

    private void menuItemAdicionarEntidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarEntidadeActionPerformed
        var janela = new JanelaEditarEntidade(null);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarEntidadeActionPerformed

    private void menuItemListagemEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemEntidadesActionPerformed
        var janelaListagem = new JanelaListagemEntidades(desktopPane, colaboradorAutenticado);
        desktopPane.add(janelaListagem);
        janelaListagem.setVisible(true);
    }//GEN-LAST:event_menuItemListagemEntidadesActionPerformed

    private void menuEstatisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEstatisticasActionPerformed

    }//GEN-LAST:event_menuEstatisticasActionPerformed

    private void buttonNomeDoUtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNomeDoUtilizadorActionPerformed
        buttonNomeDoUtilizador.setText(colaboradorAutenticado.getNome());
        var janela = new JanelaEditarColaborador(colaboradorAutenticado, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_buttonNomeDoUtilizadorActionPerformed

    private void menuItemAdicionarColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAdicionarColaboradorActionPerformed
        var janela = new JanelaEditarColaborador(null, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemAdicionarColaboradorActionPerformed

    private void menuItemListagemColaboradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListagemColaboradoresActionPerformed
        var janela = new JanelaListagemColaboradores(desktopPane, colaboradorAutenticado);
        desktopPane.add(janela);
        janela.setVisible(true);

    }//GEN-LAST:event_menuItemListagemColaboradoresActionPerformed

    private void menuItemNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNovaVendaActionPerformed
        var janela = new JanelaEditarTransacao(null, colaboradorAutenticado, TipoTransacao.VENDA);
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemNovaVendaActionPerformed

    private void menuItemVisualizarEstatisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVisualizarEstatisticasActionPerformed
        var janela = new JanelaEstatisticas();
        desktopPane.add(janela);
        janela.setVisible(true);
    }//GEN-LAST:event_menuItemVisualizarEstatisticasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNomeDoUtilizador;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu menuArmazem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuColaboradores;
    private javax.swing.JMenu menuEntidades;
    private javax.swing.JMenu menuEstabelecimento;
    private javax.swing.JMenu menuEstatisticas;
    private javax.swing.JMenu menuEventos;
    private javax.swing.JMenu menuIntervencoes;
    private javax.swing.JMenuItem menuItemAdicionarArtigo;
    private javax.swing.JMenuItem menuItemAdicionarColaborador;
    private javax.swing.JMenuItem menuItemAdicionarEntidade;
    private javax.swing.JMenuItem menuItemAdicionarEstabelecimento;
    private javax.swing.JMenuItem menuItemAdicionarEventos;
    private javax.swing.JMenuItem menuItemAdicionarIntervencao;
    private javax.swing.JMenuItem menuItemAdicionarVeiculo;
    private javax.swing.JMenuItem menuItemListagemArtigos;
    private javax.swing.JMenuItem menuItemListagemColaboradores;
    private javax.swing.JMenuItem menuItemListagemEntidades;
    private javax.swing.JMenuItem menuItemListagemEstabelecimentos;
    private javax.swing.JMenuItem menuItemListagemEventos;
    private javax.swing.JMenuItem menuItemListagemIntervencoes;
    private javax.swing.JMenuItem menuItemListagemTransacoes;
    private javax.swing.JMenuItem menuItemListagemVeiculos;
    private javax.swing.JMenuItem menuItemNovaCompra;
    private javax.swing.JMenuItem menuItemNovaVenda;
    private javax.swing.JMenuItem menuItemVisualizarEstatisticas;
    private javax.swing.JMenu menuTransacoes;
    private javax.swing.JMenu menuVeiculos;
    private javax.swing.JToolBar toolBarPrincipal;
    // End of variables declaration//GEN-END:variables

}
