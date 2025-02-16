package com.sri.GoEcho.config;

import com.sri.GoEcho.jwt.JWTService;
import com.sri.GoEcho.jwt.MyUserDetails;
import com.sri.GoEcho.jwt.MyUserDetailsService;
import com.sri.GoEcho.jwt.NotificationService;
import com.sri.GoEcho.user.ActiveAppUsers;
import com.sri.GoEcho.user.ActiveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
private JWTService jwtService;
private ActiveAppUsers activeAppUsers;
private NotificationService notificationService;

private MyUserDetailsService myUserDetailsService;




    public WebSocketConfig(JWTService jwtService, ActiveAppUsers activeAppUsers, NotificationService notificationService, MyUserDetailsService myUserDetailsService) {
        this.jwtService = jwtService;
        this.activeAppUsers = activeAppUsers;
        this.notificationService = notificationService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/GoEcho-Live").setAllowedOrigins("http://localhost:3001","http://localhost:3000","https://goecho.onrender.com");
        registry.addEndpoint("/GoEcho-Live").setAllowedOrigins("http://localhost:3001","http://localhost:3000","https://goecho.onrender.com").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");


    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);


                assert accessor != null;
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {

                    String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
                    assert authorizationHeader != null;
                    String token = authorizationHeader.substring(7);

                    String username =jwtService.extractUserName(token);
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    accessor.setUser(usernamePasswordAuthenticationToken);


                    activeAppUsers.addAppUser(accessor.getSessionId(),new ActiveUser(username));

                }

                return message;
            }

        });
    }



}
