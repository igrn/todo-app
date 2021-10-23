package igrn.todo.repository.specification;

import igrn.todo.dto.user.filter.UserFilterDto;
import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class UserSpecification {

    public static Specification<User> findUsers(Collection<UserFilterDto> filters) {
        return (users, criteriaQuery, criteriaBuilder) -> {
            Fetch<User, Role> rolesFetch = users.fetch("roles", JoinType.LEFT);
            Join<User, Role> roles = (Join<User, Role>) rolesFetch;

            Predicate[] predicates = filters.stream()
                    .map(filter -> createPredicate(users, roles, criteriaBuilder, filter))
                    .toArray(Predicate[]::new);
            return criteriaBuilder.and(predicates);
        };
    }

    //FIXME: default -> IllegalArgumentException не работает, уже перехвачено Jackson-ом
    private static Predicate createPredicate(Root<User> users, Join<User, Role> roles,
                                             CriteriaBuilder cb, UserFilterDto filter) {
        Collection<String> constraints = filter.getValues();
        return switch (filter.getUserField()) {
            case ID -> cb.in(users.get("id")).value(toInteger(constraints));
            case EMAIL -> cb.in(users.get("email")).value(constraints);
            case ROLES -> cb.in(roles.get("code")).value(constraints);
        };
    }

    private static Set<Integer> toInteger(Collection<String> strings)
                                                        throws NumberFormatException {
        try {
            return strings.stream().map(Integer::parseInt).collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid argument: " + e.getMessage());
        }
    }
}
