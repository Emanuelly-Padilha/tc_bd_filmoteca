package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.model.Watchlist;
import br.edu.ifpr.model.dao.WatchlistDAO;

public class WatchlistController {
    WatchlistDAO dao = new WatchlistDAO();

    public void adicionar(Watchlist w) {
        dao.inserir(w);
    }

    public List<Watchlist> listar(String nomeUsuario) {
        return dao.listarPorUsuario(nomeUsuario);
    }

    public void remover(Watchlist w) {
        dao.remover(w);
    }
}
