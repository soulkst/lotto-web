package dev.kirin.toy.lottoweb.core.history.repository;

import dev.kirin.toy.lottoweb.common.code.ListOrder;
import dev.kirin.toy.lottoweb.core.history.entity.AppHistory;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistorySearchParam;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Setter
@Slf4j
public class AppHistoryDAO {
    private final AppHistoryRepository repository;
    private final EntityManager entityManager;

    @Transactional
    public AppHistory save(AppHistory history) {
        return repository.save(history);
    }

    public Page<AppHistory> findAllPageable(AppHistorySearchParam param, int page, int limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppHistory> query = builder.createQuery(AppHistory.class);
        Root<AppHistory> root = query.from(AppHistory.class);

        List<Predicate> predicates = new ArrayList<>();
        if(!StringUtils.hasText(param.getCategory())) {
            predicates.add(builder.equal(root.get("historyCategory"), param.getCategory()));
        }
        if(!StringUtils.hasText(param.getType())) {
            predicates.add(builder.equal(root.get("historyType"), param.getType()));
        }
        if(!StringUtils.hasText(param.getLevel())) {
            predicates.add(builder.equal(root.get("historyLevel"), param.getType()));
        }
        if(!StringUtils.hasText(param.getMessage())) {
            predicates.add(builder.like(root.get("message"), "%" + param.getMessage() + "%"));
        }
        if(param.getFromDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("historyDate"), param.getFromDate()));
        }
        if(param.getToDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("historyDate"), param.getToDate()));
        }

        List<Order> orders = null;
        if(param.getOrder() != null) {
            orders = param.getOrder().stream()
                    .map(listOrder -> {
                        if(ListOrder.ASC.equals(listOrder.getOrder())) {
                            return builder.asc(root.get(listOrder.getField()));
                        }
                        return builder.desc(root.get(listOrder.getField()));
                    })
                    .collect(Collectors.toList());
        }

        CriteriaQuery<AppHistory> selectQuery = query.select(root);
        if(predicates.size() > 0) {
            selectQuery.where(predicates.toArray(new Predicate[0]));
        }
        if(orders != null) {
            selectQuery.orderBy(orders);
        }
        List<AppHistory> resultList = Collections.emptyList();
        if(selectQuery != null) {
            TypedQuery<AppHistory> pageableQuery = entityManager.createQuery(selectQuery);
            resultList = pageableQuery.getResultList();
        }
        return new PageImpl<>(resultList, PageRequest.of(page, limit), resultList.size());
    }
}
