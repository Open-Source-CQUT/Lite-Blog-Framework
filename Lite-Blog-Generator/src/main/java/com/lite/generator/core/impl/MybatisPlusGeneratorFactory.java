package com.lite.generator.core.impl;

import com.lite.generator.core.Generator;
import com.lite.generator.core.GeneratorFactory;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/31 15:49
 */
public class MybatisPlusGeneratorFactory implements GeneratorFactory {


    public static MybatisPlusGeneratorFactory getInstance(){
        return new MybatisPlusGeneratorFactory();
    }

    @Override
    public void start(Generator generator) {
        try {
            generator.generate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
