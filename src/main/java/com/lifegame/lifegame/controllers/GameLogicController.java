package com.lifegame.lifegame.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifegame.lifegame.services.GameLogic;

@Controller
public class GameLogicController {
  private GameLogic gl;

  public GameLogicController(GameLogic gl) {
    this.gl = gl;
  }

  @PostMapping("/doStep")
  public byte[][] nextLifeGameStep(@RequestParam byte[][] map) {
    return gl.doStep(map);
  }

  @GetMapping("/clear")
  public String clearSession(HttpSession session){
    session.setAttribute("map", null);
    return "redirect:/";
  }

  @GetMapping("/generateRandom")
  public String generateRandomMapSession(HttpSession session){
    session.setAttribute("map", gl.createRandomMap(200));
    return "redirect:/";
  }


  @GetMapping("/")
  public String getMain(HttpSession session, Model model){
    byte[][] map = (byte[][])session.getAttribute("map");
    map = gl.doStep(map);
    session.setAttribute("map", map);
    model.addAttribute("maps", gl.printMap(map));
    return "index.html";
  }
}
