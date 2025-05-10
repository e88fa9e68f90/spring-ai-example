/* 
* Copyright 2025 - 2025 the original author or authors.
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
* https://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.ai.mcp.sample.server.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.mcp.sample.server.entity.PolicyResponse;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.CreateMessageResult;
import io.modelcontextprotocol.spec.McpSchema.ModelPreferences;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Christian Tzolov
 */
@Service
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private final RestClient restClient;

    public WeatherService() {
        this.restClient = RestClient.create();
    }

    /**
     * The response format from the Open-Meteo API
     */
    public record WeatherResponse(Current current) {
        public record Current(LocalDateTime time, int interval, double temperature_2m) {
        }
    }

    @Tool(description = "根据城市名称获取天气预报")
    public String getWeatherByCity(String city) {
        Map<String, String> mockData = Map.of("北京", "大雨", "上海", "晴天", "天津", "小雨", "西安", "阴天");
        return mockData.getOrDefault(city, "抱歉：未查询到对应城市！");
    }

    @Tool(description = "根据个人信息查询用理赔列表数据")
    public String getCliams(String jwt) {
        LOGGER.info("查询根据个人信息查询用理赔列表数据，" + jwt);
        String json = this.getClaimsHttp(jwt);
        LOGGER.info("根据个人信息查询用理赔列表数据:{}", json);
        return json;
    }

    @Tool(description = "根据个人信息查询保单列表数据")
    public PolicyResponse getPolicys(
            @ToolParam(description = "jwt用户授权") String jwt) throws JsonMappingException, JsonProcessingException {
        LOGGER.info("根据个人信息查询保单列表数据，" + jwt);
        String json = this.getPolicysHttp(jwt);
        LOGGER.info("根据个人信息查询保单列表数据:{}", json);
        ObjectMapper ob = new ObjectMapper();
        return ob.readValue(json, PolicyResponse.class);
    }

    @Tool(description = "根据个人信息查询保单详情数据")
    public String getPolicyDetail(@ToolParam(description = "jwt用户授权") String jwt,
            @ToolParam(description = "要查询的保单号") String policyNo) {
        LOGGER.info("根据个人信息查询保单详情数据，" + jwt);
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        Request request = new Request.Builder()
                .url("https://pensionlife.95522.cn/gtw/policy-web/policy/v2/get-policy-detail.json?policyNo=" + policyNo
                        + "&systemCode=EBA&clue-id=892cfa48408172ac0080962f4fa4666e")
                .get().header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer " + jwt).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求保单详情服务异常！";
        }
    }

    @Tool(description = "根据个人信息查询某个保单下的功能服务菜单")
    public String getPolicysDetailMenu(@ToolParam(description = "jwt用户授权") String jwt,
            @ToolParam(description = "要查询的保单号") String policyNo) {
        LOGGER.info("根据个人信息查询某个保单下的功能服务菜单，" + jwt);
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        Request request = new Request.Builder()
                .url("https://pensionlife.95522.cn/gtw/policy-web/policy/v2/policy-unify-menu-list.json?policyNo="
                        + policyNo + "&systemCode=EBA")
                .get()
                .header("Content-Type", "application/json; charset=utf-8").header("Authorization", "Bearer " + jwt)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求保单详情服务异常！";
        }
    }

    private String getClaimsHttp(String jwt) {
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        Request request = new Request.Builder().url(
                "https://pensionlife.95522.cn/tkp-wechat-claims/claim/query/v2/list")
                .get().header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", jwt).build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求理赔查询异常";
        }
    }

    public String getPolicysHttp(String jwt) {
        OkHttpClient client = new OkHttpClient();
        // 构建请求体（这里以简单的表单形式为例）
        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody jsonBody = RequestBody.create("[\"0\", \"2\", \"1\"]", jsonMediaType);
        // 构建请求
        Request request = new Request.Builder().url(
                "https://pensionlife.95522.cn/gtw/policy-web/policy/v2/get-policy-list.json?clue-id=892cfa48408172ac0080962f4fa4666e")
                .post(jsonBody)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer " + jwt).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "请求保单查询异常";
        }
    }
    public String callMcpSampling(ToolContext toolContext, WeatherResponse weatherResponse) {
        String openAiWeatherPoem = "<no OpenAI poem>";
        String anthropicWeatherPoem = "<no Anthropic poem>";
        if (toolContext != null && toolContext.getContext().containsKey("exchange")) {
            McpSyncServerExchange exchange = (McpSyncServerExchange) toolContext.getContext().get("exchange");
            if (exchange.getClientCapabilities().sampling() != null) {
                var messageRequestBuilder = McpSchema.CreateMessageRequest.builder().systemPrompt("You are a poet!")
                        .messages(List.of(new McpSchema.SamplingMessage(McpSchema.Role.USER, new McpSchema.TextContent(
                                "Please write a poem about thius weather forecast (temperature is in Celsious). Use markdown format :\n "
                                        + ModelOptionsUtils.toJsonStringPrettyPrinter(weatherResponse)))));

                var opeAiLlmMessageRequest = messageRequestBuilder
                        .modelPreferences(ModelPreferences.builder().addHint("openai").build()).build();
                CreateMessageResult openAiLlmResponse = exchange.createMessage(opeAiLlmMessageRequest);

                openAiWeatherPoem = ((McpSchema.TextContent) openAiLlmResponse.content()).text();

                var anthropicLlmMessageRequest = messageRequestBuilder
                        .modelPreferences(ModelPreferences.builder().addHint("anthropic").build()).build();
                CreateMessageResult anthropicAiLlmResponse = exchange.createMessage(anthropicLlmMessageRequest);

                anthropicWeatherPoem = ((McpSchema.TextContent) anthropicAiLlmResponse.content()).text();

            }
        }
        String responseWithPoems = "OpenAI poem about the weather: " + openAiWeatherPoem + "\n\n"
                + "Anthropic poem about the weather: " + anthropicWeatherPoem + "\n"
                + ModelOptionsUtils.toJsonStringPrettyPrinter(weatherResponse);
        LOGGER.info(anthropicWeatherPoem, responseWithPoems);
        return responseWithPoems;

    }

}