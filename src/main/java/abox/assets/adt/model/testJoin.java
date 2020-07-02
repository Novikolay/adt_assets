//package abox.assets.adt.model;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.JoinType;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//public class testJoin {
//    // SELECT banner1 FROM Banners banner2
//    private static EntityManagerFactory entityManagerFactory =
//            Persistence.createEntityManagerFactory("example-unit");
//
//    //@Test
//    public void shouldFindWithCriteriaAPI() {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
////        //CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
////        CriteriaQuery<InventoryItemSumReport> query = criteriaBuilder.createQuery(InventoryItemSumReport.class); //InventoryItemSumReport
////        Root<InventoryItemDetail> from = query.from(InventoryItemDetail.class); //InventoryItemDetail
////
////        Join<InventoryItemDetail, InventoryItem> joinItem = from.join(InventoryItemDetail_.inventoryItem);
////
////        Predicate where = criteriaBuilder.lessThanOrEqualTo(from.get(InventoryItemDetail_.effectiveDate), date);
////
////        Join<InventoryItem, Customer> joinCustomer = joinItem.join(InventoryItem_.customer, JoinType.LEFT);
////        query.multiselect(joinItem.get(InventoryItem_.product),joinItem.get(InventoryItem_.facility),joinItem.get(InventoryItem_.customer));
////        query.groupBy(joinItem.get(InventoryItem_.product),joinItem.get(InventoryItem_.facility),joinCustomer);
////        query.where(where);
////
////        TypedQuery<InventoryItemSumReport> createQuery = getEm().createQuery(query);
////        List<InventoryItemSumReport> resultList = createQuery.getResultList();
//
//
////        EntityManager em = entityManagerFactory.createEntityManager();
////        Banner banner1 = new Banner();
////        em.persist(banner1);
////        CriteriaBuilder cb = em.getCriteriaBuilder();
////        CriteriaQuery<Banner> query = cb.createQuery(Banner.class);
////        Root<Banner> banner2 = query.from(Banner.class);
////        query.select(banner2);
////        List<Banner> resultList = em.createQuery(query).getResultList();
//////        assertEquals(1, resultList.size());
////        resultList.forEach(System.out::println);
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Banner> query = criteriaBuilder.createQuery(Banner.class);
//        Root<Banner> banner = query.from(Banner.class);
//        banner.join(Banner_.tasks, JoinType.LEFT);
//        query.select(banner).distinct(true);
//        TypedQuery<Banner> typedQuery = entityManager.createQuery(query);
//        List<Banner> resultList = typedQuery.getResultList();
//        resultList.forEach(System.out::println);
//    }
//
//
//}
