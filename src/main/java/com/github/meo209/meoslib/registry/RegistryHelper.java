package com.github.meo209.meoslib.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public class RegistryHelper {

    public static String toRegistryName(String name) {
        return name.replace(' ', '_').toUpperCase();
    }

    private static <T> Registry<T> getRegistry(MinecraftRegistry modRegistry) throws NoSuchFieldException, IllegalAccessException {
        return (Registry<T>) Registries.class.getField(toRegistryName(modRegistry.name())).get(Registries.class);
    }

    public static <T> void register(Class<?> clazz, String modId) throws IllegalAccessException, NoSuchFieldException {
        if (clazz.isAnnotationPresent(ModRegistry.class)) {
            for (Field field : clazz.getFields()) {
                ModRegistry modRegistry = clazz.getAnnotation(ModRegistry.class);
                Registry<T> registry = getRegistry(modRegistry.value());
                T object = (T) field.get(clazz);

                if (!object.getClass().getName().equals(modRegistry.value().clazz.getName()))
                    throw new RuntimeException("Cannot register a " + object.getClass().getName()
                            + " in " + modRegistry.value().name() + " registry.");

                Identifier identifier = new Identifier(modId, field.getName().toLowerCase());

                if (field.isAnnotationPresent(Named.class))
                    identifier = new Identifier(modId, field.getAnnotation(Named.class).value());

                Registry.register(registry, identifier, object);
            }
        } else {
            throw new RuntimeException("Expected @ModRegistry annotation in " + clazz.getName());
        }
    }



}
