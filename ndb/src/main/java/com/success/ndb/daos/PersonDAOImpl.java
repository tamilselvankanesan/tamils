package com.success.ndb.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;
import com.success.ndb.entities.Person_;

public class PersonDAOImpl implements PersonDAOCustom {

	/*
	 * If the name of your jpa repository interface is LocaleJpaRepository, your
	 * new custom interface should be named LocaleJpaRepositoryCustom, but the
	 * class that makes the override in the method must be named
	 * LocaleJpaRepositoryImpl, as it follows:
	 * 
	 * public class LocalJpaRepositoryImpl implements LocalJpaRepositoryCustom{
	 * 
	 * @Override public void customMethod(){....} } Basically, the
	 * implementation class of your custom interface should start with the name
	 * of your repository interface (JPARepository) ending with 'Impl' keyword.
	 */
	@Autowired
	private EntityManager em;

	@Override
	public List<Person> search(PersonDTO dto) {

		// CriteriaQuery<Person> query = em
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> root = cq.from(Person.class);
		cq.select(root);

		cq.where(getConditions(cb, root, dto).toArray(new Predicate[0]));
		TypedQuery<Person> tq = em.createQuery(cq);

		return tq.getResultList();
	}

	private List<Predicate> getConditions(CriteriaBuilder cb, Root<Person> root, PersonDTO dto) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasLength(dto.getFirstName())) {
			predicates.add(cb.like(cb.lower(root.get(Person_.firstName)),
					"%" + dto.getFirstName().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getLastName())) {
			predicates.add(
					cb.like(cb.lower(root.get(Person_.lastName)), "%" + dto.getLastName().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getAddress1())) {
			predicates.add(
					cb.like(cb.lower(root.get(Person_.address1)), "%" + dto.getAddress1().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getAddress2())) {
			predicates.add(
					cb.like(cb.lower(root.get(Person_.address2)), "%" + dto.getAddress2().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getState())) {
			predicates.add(cb.like(cb.lower(root.get(Person_.state)), "%" + dto.getState().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getDistrict())) {
			predicates.add(
					cb.like(cb.lower(root.get(Person_.district)), "%" + dto.getDistrict().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getCity())) {
			predicates.add(cb.like(cb.lower(root.get(Person_.city)), "%" + dto.getCity().trim().toLowerCase() + "%"));
		}
		if (StringUtils.hasLength(dto.getVillage())) {
			predicates.add(
					cb.like(cb.lower(root.get(Person_.village)), "%" + dto.getVillage().trim().toLowerCase() + "%"));
		}
		if (dto.getZipCode() != 0) {
			predicates.add(
					cb.like(root.get(Person_.zipCode).as(String.class), "%" + String.valueOf(dto.getZipCode()) + "%"));
		}
		return predicates;
	}

	@Override
	public List<Person> search(String param) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> root = cq.from(Person.class);

		cq.where(cb.or(getSearchPredicates(cb, root, param).toArray(new Predicate[0])));
		
		TypedQuery<Person> tq = em.createQuery(cq);

		return tq.getResultList();
	}

	private List<Predicate> getSearchPredicates(CriteriaBuilder cb, Root<Person> root, String param) {
		List<Predicate> orPredicates = new ArrayList<>();
		param = "%" + param.trim().toLowerCase() + "%";
		orPredicates.add(cb.like(cb.lower(root.get(Person_.firstName)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.lastName)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.address1)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.address2)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.about)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.village)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.city)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.district)), param));
		orPredicates.add(cb.like(cb.lower(root.get(Person_.state)), param));
		orPredicates.add(cb.like(root.get(Person_.zipCode).as(String.class), param));
		return orPredicates;
	}
}
