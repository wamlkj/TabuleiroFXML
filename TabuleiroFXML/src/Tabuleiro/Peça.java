// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 30/11/2017 19:27:40
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Source File Name:   Peça.java
package Tabuleiro;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public abstract class Peça extends ImageView{

    protected int Cor;
    protected int X;
    protected int Y;
    protected char Formato;
    final int TamCasa = 50;
    final int FIM = -200;
    final int SeparaRumos = -100;
    
    public Peça(String url) {
        
        super(url);
    }
    
    public abstract ArrayList<Integer> TraçarCaminhos(int X, int Y);

    public abstract ArrayList<Integer> TraçarCaptura(int X, int Y);
}
