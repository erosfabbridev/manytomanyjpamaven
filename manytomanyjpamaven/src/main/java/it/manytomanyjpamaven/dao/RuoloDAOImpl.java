package it.manytomanyjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.query.NativeQuery;

import it.manytomanyjpamaven.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ruolo> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ruolo get(Long id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo ruoloInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ruoloInstance);

	}

	@Override
	public void delete(Ruolo ruoloInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione).setParameter(2, codice);

		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<String> findDescrizioniRuoliConUtentiAssociati() throws Exception {

		Query query = entityManager.createNativeQuery(
				"select distinct ruolo.descrizione from ruolo join utente_ruolo on ruolo.id = utente_ruolo.ruolo_id  join utente on utente_ruolo.utente_id = utente.id;");
		return (List<String>) query.getResultList();
	}

}
