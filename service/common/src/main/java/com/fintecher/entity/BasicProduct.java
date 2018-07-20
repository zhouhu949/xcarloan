package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "basic_product")
@ApiModel("车型产品实体")
public class BasicProduct extends BaseEntity {
    @ApiModelProperty("方案ID")
    @Column(name = "scheme_id")
    private Long schemeId;
    @ApiModelProperty("配置ID")
    @Column(name = "config_id")
    private Long configId;
    @ApiModelProperty("机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty("产品名称")
    @Column(name = "product_name")
    private String productName;
    @ApiModelProperty("路径")
    @Column(name = "file_url")
    private String fileUrl;
    @ApiModelProperty("产品类型")
    @Column(name = "product_type")
    private Integer productType;
    @ApiModelProperty("产品是否发布")
    @Column(name = "product_status")
    private Integer productStatus;


    /**
     * 产品类型
     */
    public enum ProductType {
        FINANCING(10049, "融资租赁"),
        MORTGAGE(10050, "抵押贷款");

        private Integer value;
        private String remark;

        ProductType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (ProductType type : ProductType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 产品发布状态
     */
    public enum ProductStatus {
        PUBLISHED_NO(10056, "未发布"),
        PUBLISHED_YES(10057, "已发布");

        private Integer value;
        private String remark;

        ProductStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (ProductStatus type : ProductStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
