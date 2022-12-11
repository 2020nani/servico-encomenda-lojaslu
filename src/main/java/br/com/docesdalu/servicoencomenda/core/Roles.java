package br.com.docesdalu.servicoencomenda.core;

import br.com.docesdalu.servicoencomenda.core.enums.RoleAcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roles{
    private Long id;
    private RoleAcess roleAcess;
}
