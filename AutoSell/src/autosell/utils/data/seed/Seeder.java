package autosell.utils.data.seed;

import autosell.enumeracoes.*;
import autosell.modelos.*;
import autosell.gestores.*;
import java.time.LocalDate;
import static autosell.utils.DateUtil.parseLocalDate;

public class Seeder {

    public Seeder() {
        
        // <editor-fold defaultstate="collapsed" desc="Estabelecimentos">
        Estabelecimento e1 = new Estabelecimento(
                "Lisboa - AutoSell", 
                "lisboa@autosell.pt",
                "221 684 985", 
                "Rua da Azeitona fresca, Santo Amargo, Lisboa", 
                4500, 
                TipoEstabelecimento.SEDE);
        
        Estabelecimento e2 = new Estabelecimento(
                "Leiria - AutoSell", 
                "leiria@autosell.pt", 
                "241 987 653", 
                "Travesa das Águas frias, Cimo do monte, Parceiros, 2400-815, Leiria", 
                100, 
                TipoEstabelecimento.FILIAL);
        
        Estabelecimento e3 = new Estabelecimento(
                "Porto prestige - AutoSell", 
                "porto@autosell.pt", 
                "357 357 942", 
                "Rua ao pé da ponte do Luís, Porto", 
                3, 
                TipoEstabelecimento.FILIAL);
        
        GestorEstabelecimentos.getInstance().adicionar(e1);
        GestorEstabelecimentos.getInstance().adicionar(e2);
        GestorEstabelecimentos.getInstance().adicionar(e3);

        // </editor-fold> 
   
        // <editor-fold defaultstate="collapsed" desc="Colaboradores">
        Colaborador c0 = new Colaborador("Administrador", 
                "admin@autosell.pt", 
                e2, 
                "1234", 
                TipoColaborador.ADMINISTRADOR);
        
        Colaborador c1 = new Colaborador(
                "Roberto Fonseca Dionísio", 
                "roberto.dionisio@autosell.pt", 
                e1, 
                "1234", 
                TipoColaborador.ADMINISTRADOR);
        
        Colaborador c2 = new Colaborador(
                "Inácia Luísa Mendes da Silva", 
                "inacia.silva@autosell.pt", 
                e1, 
                "1234", 
                TipoColaborador.COMERCIAL);
        
        Colaborador c3 = new Colaborador(
                "Francisco Mendonsa", 
                "francisco.mendonsa@autosell.pt", 
                e2, 
                "1234", 
                TipoColaborador.COMERCIAL);
        
        Colaborador c4 = new Colaborador(
                "Rodrigo Carvalho", 
                "rodrigo.carvalho@autosell.pt", 
                e3, 
                "1234", 
                TipoColaborador.COMERCIAL);
        
        
        Colaborador c5 = new Colaborador(
                "Aquelides da Mendonsa", 
                "aquelides.mendonsa@autosell.pt", 
                e1, 
                "1234", 
                TipoColaborador.TECNICO);
        
        Colaborador c6 = new Colaborador(
                "Jacinto Leite", 
                "jacineto.leite@autosell.pt", 
                e2, 
                "1234", 
                TipoColaborador.TECNICO);

        Colaborador c7 = new Colaborador(
                "Luís Miguel da Fonseca Lurdes", 
                "luis.lurdes@autosell.pt", 
                e3, 
                "1234", 
                TipoColaborador.TECNICO);
        
        GestorColaboradores.getInstance().adicionar(c0);
        GestorColaboradores.getInstance().adicionar(c1);
        GestorColaboradores.getInstance().adicionar(c2);
        GestorColaboradores.getInstance().adicionar(c3);
        GestorColaboradores.getInstance().adicionar(c4);
        GestorColaboradores.getInstance().adicionar(c5);
        GestorColaboradores.getInstance().adicionar(c6);
        GestorColaboradores.getInstance().adicionar(c7);

        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="Veículos">
        Veiculo v1 = new Veiculo(10.968F, "AA-95-35", "Renault", e1);
        v1.setModelo("Clio");
        v1.setMesRegisto(MesesAno.AGOSTO);
        v1.setCaracteristicas("Bluetooth e ar condicionado.");
        v1.setCelindrada(1100);
        v1.setClasse(ClassesVeiculos.CLASSE_1);
        v1.setCombustivel(TipoCombustivel.GASOLINA);
        v1.setCor("Azul");
        v1.setLotacao(5);
        v1.setNumeorMudancas(5);
        v1.setNumeroPortas(3);
        v1.setPotencia(75);
        v1.setQuilometros(560654);
        v1.setAnoRegisto(1995);
        
        Veiculo v2 = new Veiculo(25983.55F, "ZZ-99-AA", "BMW", e1);
        v2.setModelo("X5");
        v2.setMesRegisto(MesesAno.JANEIRO);
        v2.setCaracteristicas("n/a");
        v2.setCelindrada(1800);
        v2.setClasse(ClassesVeiculos.CLASSE_2);
        v2.setCombustivel(TipoCombustivel.GASOLEO);
        v2.setCor("Branco");
        v2.setLotacao(5);
        v2.setNumeorMudancas(6);
        v2.setNumeroPortas(5);
        v2.setPotencia(125);
        v2.setQuilometros(159435);
        v2.setObservacoes("Ainda cheira a novo.");
        v2.setAnoRegisto(2021);
        
        Veiculo v3 = new Veiculo(6522F, "FF-33-53", "Seat", e1);
        v3.setModelo("Ibiza");
        v3.setMesRegisto(MesesAno.MAIO);
        v3.setCaracteristicas("- Radio a CDs");
        v3.setCelindrada(1990);
        v3.setClasse(ClassesVeiculos.CLASSE_1);
        v3.setCombustivel(TipoCombustivel.GASOLEO);
        v3.setCor("Vermelho");
        v3.setLotacao(2);
        v3.setNumeorMudancas(5);
        v3.setNumeroPortas(5);
        v3.setPotencia(96);
        v3.setQuilometros(357951);
        v3.setObservacoes("O carro tem algumas deformações no banco do pendura.");
        v3.setAnoRegisto(1998);
        
        Veiculo v4 = new Veiculo(9535F, "FA-76-98", "Seat", e2);
        v4.setModelo("Mondeo");
        v4.setMesRegisto(MesesAno.NOVEMBRO);
        v4.setCaracteristicas("- Ar condicionado");
        v4.setCelindrada(1500);
        v4.setClasse(ClassesVeiculos.CLASSE_1);
        v4.setCombustivel(TipoCombustivel.GASOLEO);
        v4.setCor("Preto");
        v4.setLotacao(5);
        v4.setNumeorMudancas(5);
        v4.setNumeroPortas(5);
        v4.setPotencia(110);
        v4.setQuilometros(256985);
        v4.setObservacoes("N/A");
        v4.setAnoRegisto(2001);
   
        Veiculo v5 = new Veiculo(15299F, "51-HJ-62", "Dodge", e3);
        v5.setModelo("Viper");
        v5.setMesRegisto(MesesAno.DEZEMBRO);
        v5.setCaracteristicas("Full extras");
        v5.setCelindrada(4500);
        v5.setClasse(ClassesVeiculos.CLASSE_1);
        v5.setCombustivel(TipoCombustivel.GASOLINA);
        v5.setCor("Branco");
        v5.setLotacao(2);
        v5.setNumeorMudancas(7);
        v5.setNumeroPortas(2);
        v5.setPotencia(456);
        v5.setQuilometros(25600);
        v5.setObservacoes("N/A");
        v5.setAnoRegisto(2018);
        
        Veiculo v6 = new Veiculo(256965F, "10-DE-30", "Ferrari", e3);
        v6.setModelo("488 Spider");
        v6.setMesRegisto(MesesAno.JANEIRO);
        v6.setCaracteristicas("Full extras");
        v6.setCelindrada(3902);
        v6.setClasse(ClassesVeiculos.CLASSE_1);
        v6.setCombustivel(TipoCombustivel.GASOLINA);
        v6.setCor("Prata");
        v6.setLotacao(2);
        v6.setNumeorMudancas(7);
        v6.setNumeroPortas(3);
        v6.setPotencia(670);
        v6.setQuilometros(25600);
        v6.setObservacoes("Caixa de velocidades automática.");
        v6.setAnoRegisto(2018);
        
        GestorVeiculos.getInstance().adicionar(v1);
        GestorVeiculos.getInstance().adicionar(v2);
        GestorVeiculos.getInstance().adicionar(v3);
        GestorVeiculos.getInstance().adicionar(v4);
        GestorVeiculos.getInstance().adicionar(v5);
        GestorVeiculos.getInstance().adicionar(v6);

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Entidades">
        Entidade n1 = new Entidade(
                "Henriques Mendosas.lda", 
                "35775345", 
                "91253434", 
                TipoEntidade.VENDEDOR);
        
        Entidade n2 = new Entidade(
                "Alberto Fonseca", 
                "345543545", 
                "26543454", 
                TipoEntidade.CLIENTE);
        
        Entidade n3 = new Entidade(
                "Lurdes Pimenta", 
                "951357456", 
                "357654982", 
                TipoEntidade.CLIENTE);
        
        GestorEntidades.getInstance().adicionar(n1);
        GestorEntidades.getInstance().adicionar(n2);
        GestorEntidades.getInstance().adicionar(n3);

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Eventos">
        Evento ev1 = new Evento(
                "Feira da Agrícultura", 
                parseLocalDate("01/05/2022"), 
                parseLocalDate("01/06/2022"),
                "Santarém");
        ev1.addVeiculo(v1);
        ev1.addVeiculo(v2);
        
        Evento ev2 = new Evento(
                "Feira da dos Sport Cars", 
                parseLocalDate("03/06/2022"), 
                parseLocalDate("10/08/2022"),
                "Lisboa");
        
        ev2.addVeiculo(v6);
        
        GestorEventos.getInstance().adicionar(ev1);
        GestorEventos.getInstance().adicionar(ev2);

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Artigos">
        Artigo ar1 = new Artigo(
                "PN-AZ-099", 
                "Pneu Azol Ptel 123.44", 
                50, 
                2, 
                "Unidades", 
                e1);
        
        Artigo ar2 = new Artigo(
                "OL-PTR-11", 
                "Oleo Petrol", 
                158, 
                0, 
                "Litros", 
                e1);
        
        Artigo ar3 = new Artigo(
                "PL-AA-TR", 
                "Pastilhas Trabões Bubalicios", 
                18, 
                10, 
                "Unidades", 
                e1);
        
        Artigo ar4 = new Artigo(
                "OL-TR-PTR-12", 
                "Óleo trabões Milka", 
                56, 
                2, 
                "Litros", 
                e2);
        
        Artigo ar5 = new Artigo(
                "LQ-REF-01", 
                "Liquido Refrigerante FreshTop", 
                96, 
                2, 
                "Litros", 
                e1);
        
        Artigo ar6 = new Artigo(
                "AM-REF-12", 
                "Ambientador topxuxa", 
                49, 
                2, 
                "Unidades", 
                e2);
        
        Artigo ar7 = new Artigo(
                "LP-Medio-11", 
                "Luz Medios brilhante 11", 
                96, 
                2, 
                "Unidades", 
                e1);
        
        Artigo ar8 = new Artigo(
                "VD-85-AA", 
                "Vidro lateral", 
                0, 
                1, 
                "Unidades", 
                e1);

        GestorArtigos.getInstance().adicionar(ar1);
        GestorArtigos.getInstance().adicionar(ar2);
        GestorArtigos.getInstance().adicionar(ar3);
        GestorArtigos.getInstance().adicionar(ar4);
        GestorArtigos.getInstance().adicionar(ar5);
        GestorArtigos.getInstance().adicionar(ar6);
        GestorArtigos.getInstance().adicionar(ar7);
        GestorArtigos.getInstance().adicionar(ar8);


        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Intervenções">
        Intervencao i1 = new Intervencao(
                parseLocalDate("12/12/2021"), 
                v1, 
                "Limpeza e desifeção do habitáculo.",
                c5,
                e1);
        
         Intervencao i2 = new Intervencao(
                parseLocalDate("13/12/2021"), 
                v2, 
                "Limpeza e desifeção do habitáculo.",
                c5,
                e1);
         
          Intervencao i3 = new Intervencao(
                parseLocalDate("14/12/2021"), 
                v3, 
                "Troca de vidro da porta do condutor.",
                c5,
                e1);
          i3.adidionarConsumivel(new Consumivel(
                  ar8.getReferencia(), 
                  ar8.getNome(), 
                  1, 
                  ar8.getUnidade(),
                  ar8));
        
          GestorIntervencoes.getInstance().adicionar(i1);
          GestorIntervencoes.getInstance().adicionar(i2);
          GestorIntervencoes.getInstance().adicionar(i3);

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Transações">
        Transacao t1 = new Transacao(
                TipoTransacao.COMPRA, 
                parseLocalDate("02/06/2021"), 
                c0);
        
        t1.adicionarVeiculoAssociado(v6);
        DetalhesTransacao dt1 = new DetalhesTransacao(
                "A compra foi paga", 
                "",
                -v6.getPreco(),
                TipoDetalheTransacao.TOTAL_VEICULOS);
        t1.adicionarDetalhesTransacao(dt1);
        
        GestorTransacoes.getInstance().adicionar(t1);
        // </editor-fold>
        
    }
}
