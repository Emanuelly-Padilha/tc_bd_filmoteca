package br.edu.ifpr.controller;

import java.util.List;

import br.edu.ifpr.dao.WatchlistDAO;
import br.edu.ifpr.model.Watchlist;

public class WatchlistController {
    WatchlistDAO dao = new WatchlistDAO();

    public void adicionar(Watchlist w) {
        dao.inserir(w);
    }

    public List<Watchlist> listar(String nomeUsuario) {
        return dao.listarPorUsuario(nomeUsuario);
    }
}
