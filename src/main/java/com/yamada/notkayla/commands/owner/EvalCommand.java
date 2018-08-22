package com.yamada.notkayla.commands.owner;

import com.yamada.notkayla.Kayla;
import com.yamada.notkayla.commands.Checks;
import com.yamada.notkayla.commands.Command;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;

public class EvalCommand implements Command {
//todo finish eval
    SimpleScriptContext ctx = new SimpleScriptContext();
    public EvalCommand(){
        EvalWriter r = new EvalWriter();
        ctx.setWriter(r);
    }
    @Override
    public void run(JDA bot, GuildMessageReceivedEvent event, String[] args) {
        if(!Checks.isAdmin(event.getAuthor().getId())) return;//don't even say anything, just ignore the call
        String arg = String.join(" ", args);
        //in which case go ahead
        ScriptEngine se = Kayla.registry.sf.getEngineByName("JavaScript");
        try {
            se.eval(arg);
        } catch (ScriptException e) {
            StringBuilder tbuild = new StringBuilder();
            StackTraceElement[] trace = e.getStackTrace();
            for (StackTraceElement traceElement : trace)
                tbuild.append("\tat ").append(traceElement);
            event.getChannel().sendMessage("```"+e.getMessage()+"```").submit();
            e.printStackTrace();
        }
    }
    class EvalWriter extends Writer {
        TextChannel tc = Kayla.bot.getTextChannelById("481528711720730634");

        @Override
        public void write(@NotNull char[] cbuf, int off, int len) throws IOException {
            tc.sendMessage(Arrays.toString(cbuf)).queue();
        }

        @Override
        public void flush() throws IOException {

        }

        @Override//ignore this
        public void close() {}
    }
}
