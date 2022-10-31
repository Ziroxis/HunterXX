package com.yuanno.hunterxx.particles.hatsu.jajanken;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.init.ModParticleTypes;
import com.yuanno.hunterxx.particles.GenericParticleData;
import com.yuanno.hunterxx.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ChargeUpParticleEffect extends ParticleEffect {

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
    {
        double phi = 0;
        double x, y, z;
        double radius = 0.4;
        Random random = world.random;
        int i = 0;

        while (phi < 1)
        {
            phi += 0.5 * Math.PI;
            for (double theta = 0; theta <= 1 * Math.PI; theta += Math.PI / 16)
            {

                x = radius * phi * Math.cos(theta);
                y = radius * phi * Math.cos(theta);
                z = radius * phi * Math.sin(theta);
                double offsetX = Beapi.randomDouble() * 8;
                double offsetY = Beapi.randomDouble() * 8;
                double offsetZ = Beapi.randomDouble() * 8;


                /* -> more particles on the player himself
                x = (radius * Math.cos(theta) * Math.sin(phi)) + Beapi.randomDouble();
                y = radius * Math.cos(phi) + (Beapi.randomDouble() * 2);
                z = (radius* Math.sin(theta) * Math.sin(phi)) + Beapi.randomDouble();
                 */

                motionX = x * 0.2;
                motionY = x * 0.2;
                motionZ = z * 0.2;

                GenericParticleData data;
                if(i % 3 == 0)
                    data = new GenericParticleData(ModParticleTypes.NEN_CHARGEUP.get());
                else
                    data = new GenericParticleData(ModParticleTypes.NEN_CHARGEUP.get());
                data.setLife(7);
                data.setSize(0.7F);
                data.setMotion(-(posX + x * offsetX - posX), -(posY - 0.2 + y * offsetY - posY), -(posZ + z * offsetZ - posZ));
                //data.setColor(0.7F, 0, 0.7F, 0.5F); -> purple
                data.setColor(1, 1, 1, 1);
                Beapi.spawnParticles(data, (ServerWorld) world, posX + x * offsetX, posY + 0.6 + y * offsetY, posZ + z * offsetZ);

                i++;
            }
        }
    }
}
