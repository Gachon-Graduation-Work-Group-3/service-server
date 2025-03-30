package howmuchcar.domain.query.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchOtherUserQuery {
    private String name;
    private String picture;
}
