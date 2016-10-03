/*
 * Copyright (c) 2016 Matsv
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.matsv.viabackwards.protocol.protocol1_9_4to1_10;

import lombok.Getter;
import nl.matsv.viabackwards.api.BackwardsProtocol;
import nl.matsv.viabackwards.api.storage.EntityTracker;
import nl.matsv.viabackwards.protocol.protocol1_9_4to1_10.packets.BlockItemPackets;
import nl.matsv.viabackwards.protocol.protocol1_9_4to1_10.packets.ChangedPackets;
import nl.matsv.viabackwards.protocol.protocol1_9_4to1_10.packets.EntityPackets;
import nl.matsv.viabackwards.protocol.protocol1_9_4to1_10.packets.SoundPackets;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.protocols.protocol1_9_3to1_9_1_2.storage.ClientWorld;

@Getter
public class Protocol1_9To1_10 extends BackwardsProtocol {
    private EntityPackets entityPackets; // Required for the item rewriter

    protected void registerPackets() {
        new ChangedPackets().register(this);
        new SoundPackets().register(this);
        (entityPackets = new EntityPackets()).register(this);
        new BlockItemPackets().register(this);
    }

    public void init(UserConnection user) {
        // Register ClientWorld
        if (!user.has(ClientWorld.class))
            user.put(new ClientWorld(user));

        // Register EntityTracker if it doesn't exist yet.
        if (!user.has(EntityTracker.class))
            user.put(new EntityTracker(user));
    }
}
