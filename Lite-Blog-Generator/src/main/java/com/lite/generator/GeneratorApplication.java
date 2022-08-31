package com.lite.generator;

import com.lite.generator.core.impl.MybatisPlusGeneratorFactory;
import com.lite.generator.mpg.MybatisPlusGenerator;

/**
 * @author Stranger
 * @version 1.0
 * @description: 代码生成器，若非必要，无需注入spring容器
 * @date 2022/8/31 15:27
 */

public class GeneratorApplication {

    public static void main(String[] args) {
        MybatisPlusGeneratorFactory.getInstance().start(new MybatisPlusGenerator());
    }
}
