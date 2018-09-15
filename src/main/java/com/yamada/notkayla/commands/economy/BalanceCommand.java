package com.yamada.notkayla.commands.economy;

import com.yamada.notkayla.commands.Command;
import com.yamada.notkayla.module.modules.database.DatabaseModule;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.sql.SQLException;

@Command(name="bal",description = "Please add details Allen",group="economy")
public class BalanceCommand {
    public void run(JDA bot, GuildMessageReceivedEvent event, String[] args) throws SQLException {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle("Unnamed Currency");
        b.addField("Erm lets try", String.valueOf(DatabaseModule.userData(event.getAuthor().getId()).getCoins()),false);
        event.getChannel().sendMessage(b.build()).queue();

    }
}
