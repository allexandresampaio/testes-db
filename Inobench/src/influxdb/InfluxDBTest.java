/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package influxdb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allexandre
 */
public class InfluxDBTest {

    private int qtdUser;
    private int qtdTransacoes;

    public int getQtdUser() {
        return qtdUser;
    }

    public void setQtdUser(int qtdUser) {
        this.qtdUser = qtdUser;
    }

    public int getQtdTransacoes() {
        return qtdTransacoes;
    }

    public void setQtdTransacoes(int qtdTransacoes) {
        this.qtdTransacoes = qtdTransacoes;
    }

    public void testarInsercao() throws InterruptedException {
        List threads = new ArrayList();//lista para guardar threads em execução
        for (int i = 0; i < qtdUser; i++) {
            InfluxDBInsertThread thread = new InfluxDBInsertThread("user_" + i, qtdTransacoes);
            thread.start();
            threads.add(thread);
        }
        //esperando por todas as threads finalizarem pra dar continuidade
        for (Object thread : threads) {
            ((Thread) thread).join();
        }
    }

    public void testarConsulta() throws InterruptedException {
        List threads = new ArrayList();//lista para guardar threads em execução
        for (int i = 0; i < qtdUser; i++) {
            InfluxDBReadThread thread = new InfluxDBReadThread("user_" + i, qtdTransacoes);
            thread.start();
            threads.add(thread);
        }
        //esperando por todas as threads finalizarem pra dar continuidade
        for (Object thread : threads) {
            ((Thread) thread).join();
        }
    }
}