// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 30/11/2017 19:39:29
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TabuleiroController.java
package tabuleirofxml;

import Tabuleiro.Cajahyba;
import Tabuleiro.Casa;
import Tabuleiro.Peça;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Tridente;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

// Referenced classes of package jogotabuleirofxml:
//            Tabuleiro, Casa
public class TabuleiroJanelaController implements Initializable {

    public TabuleiroJanelaController() {
        
        this.TrataCliqueDoMouse = event -> {
            
            if(event.getSource() instanceof Casa) {
                
                System.out.print(((Casa) event.getSource()).getCoordX());
                System.out.println(" " + ((Casa) event.getSource()).getCoordY());
            }
            
            if(event.getSource() instanceof ImageView) {
                
                System.out.println(event.getSource());
                System.out.println("é Peça");
                
                TreatMovements(((Peça) event.getSource()), Tab);
            }
            
        };
        
        gridPane = new GridPane();
    }

    public int getTamCasa() {

        return TamCasa;
    }

    public void setTamCasa(int TamCasa) {

        this.TamCasa = TamCasa;
    }

    public int getLinhas() {

        return Linhas;
    }

    public void setLinhas(int Linhas) {

        this.Linhas = Linhas;
    }

    public int getColunas() {

        return Colunas;
    }

    public void setColunas(int Colunas) {

        this.Colunas = Colunas;
    }

    public void MontarGrid() {

        Tab = new Tabuleiro(Linhas, Colunas);

        for (int i = 0; i < Linhas; i++) {

            RowConstraints RC = new RowConstraints();
            RC.setPrefHeight(TamCasa);
            

            gridPane.getRowConstraints().add(RC);
        }

        for (int i = 0; i < Colunas; i++) {

            ColumnConstraints RC = new ColumnConstraints();
            RC.setPrefWidth(TamCasa);

            gridPane.getColumnConstraints().add(RC);
        }

        for (int i = 0; i < Linhas; i++) {

            for (int j = 0; j < Colunas; j++) {

                Tab.getCasas()[i][j].setHeight(TamCasa);
                Tab.getCasas()[i][j].setWidth(TamCasa);
                
                Tab.getCasas()[i][j].setOnMouseClicked(TrataCliqueDoMouse);

                if (i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0) {

                    Tab.getCasas()[i][j].setFill(Color.BLACK);
                } else {

                    Tab.getCasas()[i][j].setFill(Color.ANTIQUEWHITE);
                }

                   gridPane.add(Tab.getCasas()[i][j], i, j);
                
                if(i == Linhas/2 && j == Colunas/2) {
                    
                   Peça aux = new Tridente(0, i, j);
                    
                   Tab.InserirPeça(i, j, aux);
                    
                   Tab.getCasas()[i][j].getUnidade().setOnMouseClicked(TrataCliqueDoMouse);
                    
                    gridPane.add(Tab.getCasas()[i][j].getUnidade(), i, j);
                }
                
                if(i == Linhas/3 && j == Colunas/3) {

                    Peça aux = new Cajahyba(0, i, j);
                    
                    Tab.InserirPeça(i, j, aux);
                    
                    Tab.getCasas()[i][j].getUnidade().setOnMouseClicked(TrataCliqueDoMouse);
                    
                    gridPane.add(Tab.getCasas()[i][j].getUnidade(), i, j);
                }
            }
        }

        apPrincipal.getChildren().add(gridPane);
    }
    
    ImageView png;
    
    private final EventHandler<MouseEvent> TrataCliqueDoMouse;
    
    @FXML
    public void TreatMovements(Peça Unidade, Tabuleiro Tab) {
        
        ArrayList <Integer> AllTrajetos, TrajetosVálidos = new ArrayList();
        int i, x, y;
        int All = -1;
        
        AllTrajetos = Unidade.TraçarCaminhos(All, All);
        
        TrajetosVálidos = Tab.ChecarCadaRumo(AllTrajetos);
        
        for(i = 0; i < TrajetosVálidos.size(); i += 2) {
            
            x = TrajetosVálidos.get(i);
            y = TrajetosVálidos.get(i + 1);
            
            if (x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0) {

                Tab.getCasas()[x][y].setFill(Color.DARKGREEN);    
            } else {

                Tab.getCasas()[x][y].setFill(Color.LIGHTGREEN);
            } 
        }
        
    }
    
    @FXML
    public void initialize(URL url1, ResourceBundle resourcebundle) {
        
        

    }

    private int Linhas;
    private int Colunas;
    private int TamCasa;

    private Tabuleiro Tab;
    GridPane gridPane;
    
    Casa casa;

    @FXML
    AnchorPane apPrincipal;
}
