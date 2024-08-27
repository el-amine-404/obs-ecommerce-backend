package org.obs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.obs.model.ProductCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCountDto {
    private ProductCategory category;
    private Long count;
}
