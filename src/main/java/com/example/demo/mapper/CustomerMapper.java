package com.example.demo.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Customer;

@Mapper
public interface CustomerMapper {

    /*
     * @Select("""
     * SELECT id,
     * name,
     * phone,
     * email,
     * identity_number AS identityNumber,
     * birthday,
     * status
     * FROM customers
     * """)
     */

    List<Customer> findAll();

    /*
     * @Select("""
     * SELECT id,
     * name,
     * phone,
     * email,
     * identity_number AS identityNumber,
     * birthday,
     * status
     * FROM customers
     * WHERE id = #{id}
     * """)
     */
    Customer findById(Integer id);

    /*
     * @Insert("""
     * INSERT INTO customers (
     * name,
     * phone,
     * email,
     * identity_number,
     * birthday,
     * status
     * ) VALUES (
     * #{name},
     * #{phone},
     * #{email},
     * #{identityNumber},
     * #{birthday},
     * #{status}
     * )
     * """)
     */
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Customer customer);

    /*
     * @Update("""
     * UPDATE customers
     * SET name = #{name},
     * phone = #{phone},
     * email = #{email},
     * identity_number = #{identityNumber},
     * birthday = #{birthday},
     * status = #{status}
     * WHERE id = #{id}
     * """)
     */
    int update(Customer customer);

    /*
     * @Delete("""
     * DELETE FROM customers
     * WHERE id = #{id}
     * """)
     */
    int deleteById(Integer id);
}